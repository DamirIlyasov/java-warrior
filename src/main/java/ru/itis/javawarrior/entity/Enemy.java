package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Damir Ilyasov
 */
@Getter
@Setter
public class Enemy implements CellContent {
    private int health = 50;
    private int attackPower = 50;

    @Override
    public int damage() {
        return attackPower;
    }
}
