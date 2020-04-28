package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Damir Ilyasov
 */
@Getter
@Setter
public class Stage {

    private String description;

    private int number;

    private StageCell[] cells;

    public Stage(List<StageCell> cells, int number, String description){
        this.cells = cells.toArray(new StageCell[0]);
        this.number = number;
        this.description = description;
    }

}
