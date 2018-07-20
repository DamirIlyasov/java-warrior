package ru.itis.javawarrior.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javawarrior.config.TokenAuthenticationService;
import ru.itis.javawarrior.db.model.AppUser;
import ru.itis.javawarrior.db.service.UserService;
import ru.itis.javawarrior.dto.GameResult;
import ru.itis.javawarrior.dto.SignUpDto;
import ru.itis.javawarrior.dto.UserDto;
import ru.itis.javawarrior.entity.Stage;
import ru.itis.javawarrior.entity.StageCell;
import ru.itis.javawarrior.exception.ValidateCodeException;
import ru.itis.javawarrior.json.CompileJson;
import ru.itis.javawarrior.service.CompileService;
import ru.itis.javawarrior.service.MapService;
import ru.itis.javawarrior.service.ValidateService;
import ru.itis.javawarrior.util.ban.Validation;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @Autowired
    private BCryptPasswordEncoder encoder;

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
            AppUser user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
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

    @PostMapping("/sign_up")
    public UserDto signUp(@RequestBody SignUpDto signUpDto, HttpServletResponse response) throws IOException {
        AppUser user = new AppUser();
        user.setLevel(1L);
        user.setEmail(signUpDto.getLogin());
        user.setPassword(encoder.encode(signUpDto.getPassword()));
        userService.save(user);
        UserDto userDto = new UserDto(signUpDto.getLogin(),
                1L,
                TokenAuthenticationService.addAuthenticationAfterSignUp(user.getEmail(), response)
        );
        return userDto;
    }
}
