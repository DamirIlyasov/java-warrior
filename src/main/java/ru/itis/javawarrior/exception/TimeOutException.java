package ru.itis.javawarrior.exception;

public class TimeOutException extends RuntimeException {
    public TimeOutException() {
        super("Timeout!");
    }
}
