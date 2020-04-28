package ru.itis.javawarrior.exception;

public class HeroDiedException extends RuntimeException {
    private static final String HERO_DIED = "Hero died!";

    public HeroDiedException() {
        super(HERO_DIED);
    }
}
