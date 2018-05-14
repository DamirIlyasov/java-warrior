package ru.itis.javawarrior.exception;

/**
 * @author Damir Ilyasov
 */
public class StageCompletedException extends RuntimeException {
    public StageCompletedException() {
        super("You won!");
    }
}
