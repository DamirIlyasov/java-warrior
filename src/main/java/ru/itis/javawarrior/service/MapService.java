package ru.itis.javawarrior.service;

import ru.itis.javawarrior.entity.Stage;

public interface MapService {
    Stage generateRandomMap();
    Stage getMapByLevelNumber(Integer levelNumber);
}
