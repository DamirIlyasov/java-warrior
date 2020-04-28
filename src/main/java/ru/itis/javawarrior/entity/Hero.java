package ru.itis.javawarrior.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itis.javawarrior.entity.enums.ContentType;

@Getter
@Setter
public class Hero extends CellContent{
    private int hp = 100;
    private int attackPower = 50;

    public Hero(){
        this.health = hp;
        this.damage = attackPower;
        this.contentType = ContentType.HERO;
    }
}
