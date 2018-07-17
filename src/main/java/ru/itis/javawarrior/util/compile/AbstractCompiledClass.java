package ru.itis.javawarrior.util.compile;


import ru.itis.javawarrior.entity.GameResult;
import ru.itis.javawarrior.entity.Stage;
import ru.itis.javawarrior.exception.HeroDiedException;
import ru.itis.javawarrior.exception.StageCompletedException;
import ru.itis.javawarrior.exception.TimeOutException;
import ru.itis.javawarrior.service.ActionService;
import ru.itis.javawarrior.service.MapService;
import ru.itis.javawarrior.service.impl.ActionServiceImpl;
import ru.itis.javawarrior.service.impl.MapServiceImpl;

/**
 * Класс для хранения методов, которые должен вызывать компилированный класс,
 * Также инициализирует необходимые переменные
 */
public abstract class AbstractCompiledClass implements Runner {

    private static final int AVAILABLE_ITERATIONS_NUMBER = 10;
    private boolean isActionMade;

    protected ActionService actionService;
    private MapService mapService;

    @Override
    public GameResult main(Integer levelNumber) {
        mapService = new MapServiceImpl();
        Stage map = mapService.getMapByLevelNumber(levelNumber);
        actionService = new ActionServiceImpl(map);
        try {
            int count = 0;
            while (count != AVAILABLE_ITERATIONS_NUMBER) {
                isActionMade = false;
                start();
                count++;
            }
            //тип если герой не умер и не выиграл
            throw new TimeOutException();
        } catch (StageCompletedException e) {
            return new GameResult(e.getMessage(), actionService.getActions(), true, "");
        } catch (HeroDiedException | TimeOutException e) {
            return new GameResult(e.getMessage(), actionService.getActions(), false, "");
        }

    }


    protected abstract void start() throws TimeOutException, StageCompletedException, HeroDiedException;


    protected void walk() {
        if (!isActionMade) {
            actionService.walk();
            isActionMade = true;
        }
    }

    protected void attack() {
        if (!isActionMade) {
            actionService.attack();
            isActionMade = true;
        }
    }

    protected void jump() {
        if (!isActionMade) {
            actionService.jump();
            isActionMade = true;
        }
    }

    protected void rest() {
        if (!isActionMade) {
            actionService.rest();
            isActionMade = true;
        }
    }

    protected int health() {
        return actionService.health();
    }

    protected boolean enemyAhead() {
        return actionService.isEnemyAhead();
    }

    protected boolean spikesAhead() {
        return actionService.isSpikeAhead();
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
