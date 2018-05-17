package ru.itis.javawarrior.service;

/**
 * @author Damir Ilyasov
 */
public interface Actions {
    void walk();

    void attack();

    void jump();

    boolean isEnemyAhead();

    boolean isSpikeAhead();
}
