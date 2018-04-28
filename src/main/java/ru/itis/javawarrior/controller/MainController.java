package ru.itis.javawarrior.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javawarrior.entity.GameResult;
import ru.itis.javawarrior.json.CompileJson;
import ru.itis.javawarrior.service.CompileService;
import ru.itis.javawarrior.util.ActionEnum;

/**
 * @author Damir Ilyasov
 */
@RestController
public class MainController {
    @Autowired
    private CompileService compileService;

    @ApiOperation("Index page")
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Server online!", HttpStatus.OK);
    }

    @ApiOperation("Test compile. Now can compile only walk(), jump() and attack()")
    @PostMapping("/compile")
    public ResponseEntity<GameResult> testCompile(@RequestBody CompileJson compileJson) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        GameResult result = compileService.compile(compileJson.getInputtedCode());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
