package ru.itis.javawarrior.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GoogleServiceApplication {

    @ApiOperation("Login page")
    @GetMapping("/login")
    public Principal user(Principal principal) {
        return principal;
    }
}
