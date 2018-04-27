package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Damir Ilyasov
 */
@Getter
@Setter
public class StageCell {
    private CellContent content;

    public StageCell(CellContent content) {
        this.content = content;
    }
}
