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
    private StageCell[] cells;

    public Stage(StageCell[] cells) {
        this.cells = cells;
    }

    public Stage(List<StageCell> cells){
        this.cells = cells.toArray(new StageCell[cells.size()]);
    }

}
