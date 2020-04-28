package ru.itis.javawarrior.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.itis.javawarrior.config.TokenAuthenticationService;
import ru.itis.javawarrior.controller.constants.Params;
import ru.itis.javawarrior.controller.constants.RequestDescriptions;
import ru.itis.javawarrior.controller.constants.Urls;
import ru.itis.javawarrior.db.model.AppUser;
import ru.itis.javawarrior.db.service.UserService;
import ru.itis.javawarrior.dto.GameResult;
import ru.itis.javawarrior.dto.SignUpDto;
import ru.itis.javawarrior.dto.UserDto;
import ru.itis.javawarrior.entity.Stage;
import ru.itis.javawarrior.entity.StageTemplate;
import ru.itis.javawarrior.exception.ValidateCodeException;
import ru.itis.javawarrior.json.CompileJson;
import ru.itis.javawarrior.service.CompileService;
import ru.itis.javawarrior.service.MapService;
import ru.itis.javawarrior.service.ValidateService;
import ru.itis.javawarrior.util.ban.Validation;

import javax.servlet.http.HttpServletResponse;

import static java.lang.Math.toIntExact;

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

    @CrossOrigin
    @ApiOperation(RequestDescriptions.COMPILE)
    @ApiImplicitParam(name = Params.Header.AUTHORIZATION, paramType = "header", required = true)
    @PostMapping(Urls.COMPILE)
    public ResponseEntity<GameResult> testCompile(@RequestBody CompileJson compileJson) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Validation validation = validateService.validate(compileJson.getInputtedCode());
        if (!validation.isValid()) {
            throw new ValidateCodeException(validation.getMessage());
        }
        GameResult result = compileService.compile(compileJson.getInputtedCode());
        if (result.isStageCompleted()) {
            AppUser user = userService.getCurrentUser();
            if (user.getLevel() < 4) {
                user.setLevel(user.getLevel() + 1);
            }
            userService.save(user);
        }
        return ResponseEntity.ok(result);
    }

    @ApiImplicitParam(name = Params.Header.AUTHORIZATION, paramType = "header", required = true)
    @ApiOperation(RequestDescriptions.LEVEL)
    @GetMapping(Urls.LEVEL)
    public ResponseEntity<Stage> getMapByNumber() {
        AppUser currentUser = userService.getCurrentUser();
        StageTemplate map;
        if (currentUser.getLevel() >= 4) {
            map = mapService.generateRandomMapTemplate();
        } else {
            map = mapService.getMapTemplateByLevelNumber(toIntExact(currentUser.getLevel()));
        }
        currentUser.setMap(map);
        userService.save(currentUser);
        return ResponseEntity.ok(mapService.createStageByTemplate(map));
    }

    @ApiOperation(RequestDescriptions.SIGN_UP)
    @PostMapping(Urls.SIGN_UP)
    public UserDto signUp(@RequestBody SignUpDto signUpDto, HttpServletResponse response) {
        AppUser user = new AppUser();
        user.setLevel(1L);
        user.setEmail(signUpDto.getLogin());
        user.setPassword(encoder.encode(signUpDto.getPassword()));
        userService.save(user);
        return new UserDto(signUpDto.getLogin(),
                1L,
                TokenAuthenticationService.addAuthenticationAfterSignUp(user.getEmail(), response)
        );
    }

}
