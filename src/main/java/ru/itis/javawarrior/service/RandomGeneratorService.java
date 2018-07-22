package ru.itis.javawarrior.service;

import ru.itis.javawarrior.entity.StageTemplate;

public interface RandomGeneratorService {

    StageTemplate generateMap(int number);

    StageTemplate generateMap();
}
