package ru.itis.javawarrior.util.compile;


import ru.itis.javawarrior.entity.GameResult;
import ru.itis.javawarrior.exceptions.HeroDiedException;
import ru.itis.javawarrior.exceptions.StageCompletedException;
import ru.itis.javawarrior.exceptions.TimeOutException;
import ru.itis.javawarrior.service.ActionService;
import ru.itis.javawarrior.service.impl.ActionServiceImpl;

/**
 * Класс для хранения методов, которые должен вызывать компилированный класс,
 * Также инициализирует необходимые переменные
 */
public abstract class AbstractCompiledClass implements Runner {

    private static final String DEFAULT_MESSAGE = "You lost!";
    private static final int AVAILABLE_ITERATIONS_NUMBER = 10;

    protected ActionService actionService;

    @Override
    public GameResult main() {
        actionService = new ActionServiceImpl();
        try {
            int count = 0;
            while (count != AVAILABLE_ITERATIONS_NUMBER) {
                start();
                count++;
            }
            //тип если герой не умер и не выиграл
            if (count == AVAILABLE_ITERATIONS_NUMBER)
                throw new TimeOutException();

        } catch (StageCompletedException e) {
            return new GameResult(e.getMessage(), actionService.getActions(), true, "");
        } catch (HeroDiedException | TimeOutException e) {
            return new GameResult(e.getMessage(), actionService.getActions(), false, "");
        }
        return new GameResult(DEFAULT_MESSAGE, actionService.getActions(), false, "");
    }


    protected abstract void start() throws TimeOutException, StageCompletedException, HeroDiedException;


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
