package ru.itis.javawarrior.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javawarrior.entity.GameResult;
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
    @PostMapping("/compile")
    public ResponseEntity<GameResult> testCompile(@RequestBody CompileJson compileJson,
                                                  @RequestParam(name = "level_number") Integer levelNumber) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Validation validation = validateService.validate(compileJson.getInputtedCode());
        if (!validation.isValid()) {
            throw new ValidateCodeException(validation.getMessage());
        }
        GameResult result = compileService.compile(compileJson.getInputtedCode(), levelNumber);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/level/{number}")
    public ResponseEntity<StageCell[]> getMapByNumber(@PathVariable(name = "number") Integer number) {
        Stage map = mapService.getMapByLevelNumber(number);
        return new ResponseEntity<>(map.getCells(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Principal> user(Principal principal) {
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}
