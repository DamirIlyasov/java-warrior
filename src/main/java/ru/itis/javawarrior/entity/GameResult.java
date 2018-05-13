package ru.itis.javawarrior.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.util.ActionEnum;

import java.util.List;

/**
 * @author Damir Ilyasov
 */
@Setter
@Getter
@AllArgsConstructor
public class GameResult {
    private List<ActionEnum> actions;
    private boolean isStageCompleted;
    private String errorDescription;
}
