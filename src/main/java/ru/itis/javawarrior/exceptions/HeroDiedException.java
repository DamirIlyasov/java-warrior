package ru.itis.javawarrior.exceptions;

public class HeroDiedException extends RuntimeException {
    public HeroDiedException() {
        super("Hero died!");
    }
}
