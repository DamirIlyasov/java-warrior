package ru.itis.javawarrior.service;

import ru.itis.javawarrior.util.ActionEnum;

import java.util.List;

/**
 * @author Damir Ilyasov
 */
public interface ActionService {

    void walk();

    void attack();

    void jump();

    List<ActionEnum> getActions();

}
