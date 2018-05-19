package ru.itis.javawarrior.service;

/**
 * @author Damir Ilyasov
 */
public interface Actions {

    void walk();

    void attack();

    void jump();

    void rest();

    int health();

    boolean isEnemyAhead();

    boolean isSpikeAhead();
}
