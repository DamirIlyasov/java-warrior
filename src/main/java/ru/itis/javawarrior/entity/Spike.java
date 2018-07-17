package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.entity.enums.ContentType;

/**
 * @author Damir Ilyasov
 */
@Getter
@Setter
public class Spike extends CellContent {

    public Spike() {
        this.health = 100;
        this.damage = 50;
        this.contentType = ContentType.SPIKE;
    }
}
