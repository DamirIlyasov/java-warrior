package ru.itis.javawarrior.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
}
