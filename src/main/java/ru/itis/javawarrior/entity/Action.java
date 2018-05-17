package ru.itis.javawarrior.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.util.ActionEnum;

@Getter
@Setter
@AllArgsConstructor
public class Action {
    private int heroHp;
    private ActionEnum actionEnum;
}
