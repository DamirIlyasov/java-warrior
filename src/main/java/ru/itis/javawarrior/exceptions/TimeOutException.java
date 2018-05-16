package ru.itis.javawarrior.exceptions;

public class TimeOutException extends RuntimeException {
    public TimeOutException() {
        super("Timeout!");
    }
}
