package ru.itis.javawarrior.service;

import ru.itis.javawarrior.entity.Action;

import java.util.List;

/**
 * @author Damir Ilyasov
 */
public interface ActionService extends Actions{

    List<Action> getActions();
}
