package ru.itis.javawarrior.service;

import java.util.List;

import ru.itis.javawarrior.util.ActionEnum;

/**
 * @author Damir Ilyasov
 */
public interface ActionService extends Actions{

    List<ActionEnum> getActions();
}
