package ru.itis.javawarrior.exceptions;

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
