package ru.itis.javawarrior.util.compile;


import ru.itis.javawarrior.entity.GameResult;
import ru.itis.javawarrior.exception.InvalidActionException;
import ru.itis.javawarrior.exception.StageCompletedException;
import ru.itis.javawarrior.service.ActionService;
import ru.itis.javawarrior.service.impl.ActionServiceImpl;

/**
 * Класс для хранения методов, которые должен вызывать компилированный класс,
 * Также инициализирует необходимые переменные
 */
public abstract class AbstractCompiledClass implements Runner {

    protected ActionService actionService;

    @Override
    public GameResult main() {
        actionService = new ActionServiceImpl();
        try {
            start();
        } catch (InvalidActionException e) {
            return new GameResult(actionService.getActions(), false);
        } catch (StageCompletedException e) {
            return new GameResult(actionService.getActions(), true);
        }
        return new GameResult(actionService.getActions(), false);
    }


    protected abstract void start() throws InvalidActionException, StageCompletedException;


    protected void walk() {
        actionService.walk();
    }

    protected void attack() {
        actionService.attack();
    }

    protected void jump() {
        actionService.jump();
    }

    protected void walk(int times) {
        for (int i = 0; i < times; i++)
            actionService.walk();
    }

    protected void attack(int times) {
        for (int i = 0; i < times; i++)
            actionService.attack();
    }

    protected void jump(int times) {
        for (int i = 0; i < times; i++)
            actionService.jump();
    }
}
