package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Damir Ilyasov
 */
@Getter
@Setter
public class Spike implements CellContent {
    private int damage = 100;
    private int health = 100;

    @Override
    public int damage() {
        return damage;
    }
}
