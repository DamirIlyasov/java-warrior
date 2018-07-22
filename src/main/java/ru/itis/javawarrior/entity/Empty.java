package ru.itis.javawarrior.entity;

import ru.itis.javawarrior.entity.enums.ContentType;

public class Empty extends CellContent {
    public Empty(){
        health = 0;
        damage = 0;
        contentType = ContentType.EMPTY;
    }
}
