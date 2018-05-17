package ru.itis.javawarrior.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.javawarrior.entity.GameResult;
import ru.itis.javawarrior.exception.ValidateCodeException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;


@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidateCodeException.class)
    public ResponseEntity<GameResult> validateCodeException(ValidateCodeException e) {
        return new ResponseEntity<>(new GameResult(null, null, false, e.getMessage()), OK);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GameResult> exception(Exception e) {
        return new ResponseEntity<>(new GameResult(null, null, false, e.getMessage()), INTERNAL_SERVER_ERROR);
    }
}
