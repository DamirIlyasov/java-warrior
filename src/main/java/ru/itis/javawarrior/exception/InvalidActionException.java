package ru.itis.javawarrior.exception;

/**
 * @author Damir Ilyasov
 */
public class InvalidActionException extends RuntimeException {
    public InvalidActionException() {
    }

    public InvalidActionException(String s) {
        super(s);
    }
}
