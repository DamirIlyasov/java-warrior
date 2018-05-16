package ru.itis.javawarrior.exception;

public class HeroDiedException extends RuntimeException {
    public HeroDiedException() {
        super("Hero died!");
    }
}
