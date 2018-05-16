package ru.itis.javawarrior.exceptions;

/**
 * @author Damir Ilyasov
 */
public class ValidateCodeException extends RuntimeException {

    public ValidateCodeException() {
    }

    public ValidateCodeException(String s) {
        super(s);
    }
}
