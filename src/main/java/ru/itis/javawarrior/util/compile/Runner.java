package ru.itis.javawarrior.util.compile;

import ru.itis.javawarrior.dto.GameResult;
import ru.itis.javawarrior.entity.StageTemplate;

public interface Runner {
    GameResult main(StageTemplate template);
}
