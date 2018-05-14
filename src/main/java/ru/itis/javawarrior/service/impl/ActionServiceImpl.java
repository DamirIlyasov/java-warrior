package ru.itis.javawarrior.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import ru.itis.javawarrior.entity.Enemy;
import ru.itis.javawarrior.entity.Hero;
import ru.itis.javawarrior.entity.StageCell;
import ru.itis.javawarrior.entity.Spike;
import ru.itis.javawarrior.exception.HeroDiedException;
import ru.itis.javawarrior.entity.StageCell;
import ru.itis.javawarrior.exception.InvalidActionException;
import ru.itis.javawarrior.exception.InvalidJumpException;
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
    private List<ActionEnum> responseActions;
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
            responseActions.add(ActionEnum.MOVE_FORWARD);
            throw new StageCompletedException();
        }
        if (stageCells[currentCell + 1].getContent() == null) {
            responseActions.add(ActionEnum.MOVE_FORWARD);
            currentCell++;
        }
        else {
            responseActions.add(ActionEnum.MOVE_FORWARD);
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
        responseActions.add(ActionEnum.SHOOT);
    }

    @Override
    public void jump() {
        //jump available only if cell after the next cell is empty
        //or if player on the last cell

        //jumped to finish, stage completed
        if (currentCell + 2 >= stageCells.length) {
            responseActions.add(ActionEnum.FLIP_FORWARD);
            throw new StageCompletedException();
        }
        else {
            //cell after next cell is empty
            if (stageCells[currentCell + 2].getContent() == null && !(stageCells[currentCell + 1].getContent() instanceof Enemy)) {
                responseActions.add(ActionEnum.FLIP_FORWARD);
                currentCell += 2;
            }
            else {
                responseActions.add(ActionEnum.FLIP_FORWARD);
                damageHero(stageCells[currentCell + 1].getContent().damage());
            }
        }
    }

    @Override
    public List<ActionEnum> getActions() {
        return responseActions;
    }

    private boolean isHeroAlive(){
        return hero.getHp() > 0;
    }

    private void damageHero(int damage) throws HeroDiedException {
        int currentHp = hero.getHp();
        hero.setHp(currentHp - damage);
        if (!isHeroAlive()) {
            throw new HeroDiedException();
        }
    }
    //TODO: random generation
    private StageCell[] generateStage() {
        return new StageCell[] {
            //First cell always contents null!
            new StageCell(null),
            new StageCell(new Spike()),
            new StageCell(null),
            new StageCell(new Enemy()),
            new StageCell(null)
        };
    }
}
