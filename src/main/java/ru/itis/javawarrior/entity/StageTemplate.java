package ru.itis.javawarrior.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itis.javawarrior.entity.enums.ContentType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StageTemplate {
    private ContentType[] contentTypes;
    private int number;
    private String description;

}
