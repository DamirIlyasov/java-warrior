package ru.itis.javawarrior.exception;

public class TimeOutException extends RuntimeException {
    private static final String TIMEOUT = "Timeout!";

    public TimeOutException() {
        super(TIMEOUT);
    }
}
