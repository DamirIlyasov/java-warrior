package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Damir Ilyasov
 */
@Getter
@Setter
public class Enemy implements CellContent {
    private int health;
}
