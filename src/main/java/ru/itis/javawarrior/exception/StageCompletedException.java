package ru.itis.javawarrior.exception;

/**
 * @author Damir Ilyasov
 */
public class StageCompletedException extends RuntimeException {
    private static final String YOU_WON = "You won!";

    public StageCompletedException() {
        super(YOU_WON);
    }
}
