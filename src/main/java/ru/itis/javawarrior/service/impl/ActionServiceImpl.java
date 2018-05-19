package ru.itis.javawarrior.service.impl;

import lombok.Getter;
import ru.itis.javawarrior.entity.*;
import ru.itis.javawarrior.exception.HeroDiedException;
import ru.itis.javawarrior.exception.StageCompletedException;
import ru.itis.javawarrior.service.ActionService;
import ru.itis.javawarrior.util.ActionEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Используется в компилированном классе
 */
@Getter
public class ActionServiceImpl implements ActionService {
    private static int RECOVERY = 20;

    private List<Action> responseActions;
    private int currentCell;
    private StageCell stageCells[];
    private boolean stageCompleted;
    private Hero hero;

    public ActionServiceImpl() {
        this.responseActions = new ArrayList<>();
        this.stageCells = generateStage();
        this.currentCell = 0;
        this.stageCompleted = false;
        this.hero = new Hero();
    }

    @Override
    public void walk() {
        //stage completed if player at the last cell
        if (currentCell + 1 >= stageCells.length) {
            addAction(ActionEnum.MOVE_FORWARD);
            throw new StageCompletedException();
        }
        if (stageCells[currentCell + 1].getContent() == null) {
            addAction(ActionEnum.MOVE_FORWARD);
            currentCell++;
        } else {
            addAction(ActionEnum.MOVE_FORWARD);
            damageHero(stageCells[currentCell + 1].getContent().damage());
        }
    }

    @Override
    public void attack() {
        //clearing next cell if enemy there
        //if player at the last cell adding animation only
        if (currentCell + 1 < stageCells.length) {
            stageCells[currentCell + 1].setContent(null);
        }
        addAction(ActionEnum.SHOOT);
    }

    @Override
    public void jump() {
        //jump available only if cell after the next cell is empty
        //or if player on the last cell

        //jumped to finish, stage completed
        if (currentCell + 2 >= stageCells.length) {
            addAction(ActionEnum.FLIP_FORWARD);
            throw new StageCompletedException();
        } else {
            //cell after next cell is empty
            if (stageCells[currentCell + 2].getContent() == null && !(stageCells[currentCell + 1].getContent() instanceof Enemy)) {
                addAction(ActionEnum.FLIP_FORWARD);
                currentCell += 2;
            } else {
                addAction(ActionEnum.FLIP_FORWARD);
                damageHero(stageCells[currentCell + 1].getContent().damage());
            }
        }
    }

    @Override
    public void rest() {
        hero.setHp(hero.getHp() + RECOVERY);
        addAction(ActionEnum.REST);
    }

    @Override
    public int health() {
        return hero.getHp();
    }

    @Override
    public List<Action> getActions() {
        return responseActions;
    }


    @Override
    public boolean isEnemyAhead() {
        return currentCell + 2 <= stageCells.length && stageCells[currentCell + 1].getContent() instanceof Enemy;
    }

    @Override
    public boolean isSpikeAhead() {
        return currentCell + 2 <= stageCells.length && stageCells[currentCell + 1].getContent() instanceof Spike;
    }

    private boolean isHeroAlive() {
        return hero.getHp() > 0;
    }

    private void damageHero(int damage) throws HeroDiedException {
        int currentHp = hero.getHp();
        hero.setHp(currentHp - damage);
        if (!isHeroAlive()) {
            addAction(ActionEnum.DEATH);
            throw new HeroDiedException();
        }
    }

    private void addAction(ActionEnum actionEnum) {
        Action action = new Action(hero.getHp(), actionEnum);
        responseActions.add(action);
    }

    //TODO: random generation
    private StageCell[] generateStage() {
        return new StageCell[]{
                //First cell always contents null!
                new StageCell(null),
                new StageCell(new Spike()),
                new StageCell(null),
                new StageCell(new Enemy()),
                new StageCell(null)
        };
    }
}
