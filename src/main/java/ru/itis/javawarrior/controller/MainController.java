package ru.itis.javawarrior.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import ru.itis.javawarrior.db.model.User;
import ru.itis.javawarrior.db.service.UserService;
import ru.itis.javawarrior.dto.Details;
import ru.itis.javawarrior.dto.GameResult;
import ru.itis.javawarrior.dto.UserDto;
import ru.itis.javawarrior.entity.Stage;
import ru.itis.javawarrior.entity.StageCell;
import ru.itis.javawarrior.exception.ValidateCodeException;
import ru.itis.javawarrior.json.CompileJson;
import ru.itis.javawarrior.service.CompileService;
import ru.itis.javawarrior.service.MapService;
import ru.itis.javawarrior.service.ValidateService;
import ru.itis.javawarrior.util.ban.Validation;

import java.security.Principal;

/**
 * @author Damir Ilyasov
 */
@RestController
public class MainController {
    @Autowired
    private CompileService compileService;
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("banValidateServiceImpl")
    private ValidateService validateService;

    @Autowired
    private MapService mapService;

    @ApiOperation("Index page")
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Server online!", HttpStatus.OK);
    }

    @CrossOrigin
    @ApiOperation("Available actions:\n" +
            "    walk();\n" +
            "    attack();\n" +
            "    jump();\n" +
            "    rest();\n" +
            "    health();\n" +
            "    enemyAhead();\n" +
            "    spikesAhead();"
    )

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @PostMapping("/compile")
    public ResponseEntity<GameResult> testCompile(@RequestBody CompileJson compileJson,
                                                  @RequestParam(name = "level_number") Integer levelNumber) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Validation validation = validateService.validate(compileJson.getInputtedCode());
        if (!validation.isValid()) {
            throw new ValidateCodeException(validation.getMessage());
        }
        GameResult result = compileService.compile(compileJson.getInputtedCode(), levelNumber);
        if (result.isStageCompleted()) {
            User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            if (user.getLevel() < 4) {
                user.setLevel(user.getLevel() + 1);
            }
            userService.save(user);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @GetMapping("/level/{number}")
    public ResponseEntity<StageCell[]> getMapByNumber(@PathVariable(name = "number") Integer number) {
        Stage map = mapService.getMapByLevelNumber(number);
        return new ResponseEntity<>(map.getCells(), HttpStatus.OK);
    }

    @ApiImplicitParam(name = "Authorization", paramType = "header", required = true, dataType = "string")
    @GetMapping("/user")
    public ResponseEntity<UserDto> user(Principal principal) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userService.verifyUser(principal.getName(), principal.getName());
        UserDto userDto = new UserDto(user.getName(), user.getEmail(), user.getLevel(), details.getTokenValue());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
