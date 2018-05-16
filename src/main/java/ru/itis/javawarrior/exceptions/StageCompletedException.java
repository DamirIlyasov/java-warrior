package ru.itis.javawarrior.exceptions;

/**
 * @author Damir Ilyasov
 */
public class StageCompletedException extends RuntimeException {
    public StageCompletedException() {
        super("You won!");
    }
}
