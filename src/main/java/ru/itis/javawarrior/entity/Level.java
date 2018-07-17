package ru.itis.javawarrior.entity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Level {

    public static final StageCell[] FIRST_LEVEL = new StageCell[]{
            new StageCell(null),
            new StageCell(null),
            new StageCell(null),
            new StageCell(null),
            new StageCell(null)
    };

    public static final StageCell[] SECOND_LEVEL = new StageCell[]{
            new StageCell(null),
            new StageCell(new Spike()),
            new StageCell(null),
            new StageCell(null),
            new StageCell(null)
    };

    public static final StageCell[] THIRD_LEVEL = new StageCell[]{
            new StageCell(null),
            new StageCell(new Spike()),
            new StageCell(null),
            new StageCell(new Enemy()),
            new StageCell(null)
    };

}
