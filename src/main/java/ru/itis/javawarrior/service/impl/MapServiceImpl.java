package ru.itis.javawarrior.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.entity.Enemy;
import ru.itis.javawarrior.entity.Spike;
import ru.itis.javawarrior.entity.Stage;
import ru.itis.javawarrior.entity.StageCell;
import ru.itis.javawarrior.entity.enums.ContentType;
import ru.itis.javawarrior.service.MapService;
import ru.itis.javawarrior.util.Level;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    //TODO RandomGenerator
    @Override
    public Stage generateRandomMap() {
        return new Stage(new StageCell[0]);
    }

    @Override
    public Stage getMapByLevelNumber(Integer levelNumber) {
        ContentType[] template = Level.getStageTemplate(levelNumber);
        List<StageCell> stageCells = Arrays.stream(template).map(this::transformToStageCell).collect(Collectors.toList());
        return new Stage(stageCells);
    }


    private StageCell transformToStageCell(ContentType contentType) {
        switch (contentType) {
            case EMPTY:
                return new StageCell(null);
            case ENEMY:
                return new StageCell(new Enemy());
            case SPIKE:
                return new StageCell(new Spike());
            default:
                return new StageCell(null);
        }
    }
}
