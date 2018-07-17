package ru.itis.javawarrior.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.entity.Level;
import ru.itis.javawarrior.entity.StageCell;
import ru.itis.javawarrior.service.MapService;

@Service
public class MapServiceImpl implements MapService {

    //TODO RandomGenerator
    @Override
    public StageCell[] generateRandomMap() {
        return new StageCell[0];
    }

    @Override
    public StageCell[] getMapByLevelNumber(Integer levelNumber) {
        switch (levelNumber) {
            case 1:
                return Level.FIRST_LEVEL;
            case 2:
                return Level.SECOND_LEVEL;
            case 3:
                return Level.THIRD_LEVEL;
            default:
                return generateRandomMap();
        }
    }
}
