package ru.itis.javawarrior.exception;

/**
 * @author Damir Ilyasov
 */
public class ValidateCodeException extends RuntimeException {

    public ValidateCodeException(String s) {
        super(s);
    }
}
