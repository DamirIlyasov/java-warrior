package ru.itis.javawarrior.pojo;

import lombok.Data;
import ru.itis.javawarrior.util.ActionEnum;

/**
 * @author Damir Ilyasov
 */
@Data
public class Action {
    private ActionEnum action;
    private int sequence;
}
