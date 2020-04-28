package ru.itis.javawarrior.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.javawarrior.entity.Action;

import java.util.List;

/**
 * @author Damir Ilyasov
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameResult {
    private String message;
    private List<Action> actions;
    private boolean isStageCompleted;
    private String errorDescription;

    public static GameResult error(String errorDescription) {
        GameResult gameResult = new GameResult();
        gameResult.setErrorDescription(errorDescription);
        return gameResult;
    }
}
