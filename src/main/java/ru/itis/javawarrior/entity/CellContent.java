package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.entity.enums.ContentType;

@Setter
@Getter
public abstract class CellContent {
    protected int health;
    protected int damage;
    protected ContentType contentType;
}
