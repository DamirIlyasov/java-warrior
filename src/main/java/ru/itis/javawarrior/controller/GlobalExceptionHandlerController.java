package ru.itis.javawarrior.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.itis.javawarrior.exception.ValidateCodeException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@ControllerAdvice
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidateCodeException.class)
    public ResponseEntity<String> validateCodeException(ValidateCodeException e) {
        return new ResponseEntity<>("You'r code is not valid", BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        Throwable rootCause = e.getCause();
        return new ResponseEntity<>(rootCause.getMessage(), INTERNAL_SERVER_ERROR);
    }
}
