package ru.itis.javawarrior.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.util.ActionEnum;

/**
 * @author Damir Ilyasov
 */
@Setter
@Getter
@AllArgsConstructor
public class GameResult {
    private String message;
    private List<ActionEnum> actions;
    private boolean isStageCompleted;
}
