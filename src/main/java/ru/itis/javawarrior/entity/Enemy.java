package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.entity.enums.ContentType;

/**
 * @author Damir Ilyasov
 */
@Getter
@Setter
public class Enemy extends CellContent {

    public Enemy() {
        this.health = 50;
        this.damage = 50;
        this.contentType = ContentType.ENEMY;
    }
}
