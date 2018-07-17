package ru.itis.javawarrior.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.entity.enums.ActionEnum;

@Getter
@Setter
@AllArgsConstructor
public class Action {
    private int heroHp;
    private ActionEnum actionEnum;
    private int damaged;
}
