package ru.itis.javawarrior.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Damir Ilyasov
 */
@Setter
@Getter
@AllArgsConstructor
public class GameResult {
    private String message;
    private List<Action> actions;
    private boolean isStageCompleted;
    private String errorDescription;
}
