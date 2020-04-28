package ru.itis.javawarrior.entity;

import ru.itis.javawarrior.entity.enums.ContentType;

public class Empty extends CellContent {
    public Empty(){
        this.health = 0;
        this.damage = 0;
        this.contentType = ContentType.EMPTY;
    }
}
