package ru.itis.javawarrior.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Damir Ilyasov
 */
@RestController
public class MainController {
    @ApiOperation("Index page")
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Server online!", HttpStatus.OK);
    }
}
