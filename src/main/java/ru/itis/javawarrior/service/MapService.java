package ru.itis.javawarrior.service;

import ru.itis.javawarrior.entity.StageCell;

public interface MapService {
    StageCell[] generateRandomMap();
    StageCell[] getMapByLevelNumber(Integer levelNumber);
}
