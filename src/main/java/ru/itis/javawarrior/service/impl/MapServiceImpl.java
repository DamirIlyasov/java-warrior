package ru.itis.javawarrior.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.entity.*;
import ru.itis.javawarrior.entity.enums.ContentType;
import ru.itis.javawarrior.service.MapService;
import ru.itis.javawarrior.service.RandomGeneratorService;
import ru.itis.javawarrior.util.Level;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {


    private RandomGeneratorService randomGeneratorService = new RandomGeneratorServiceImpl();

    @Override
    public StageTemplate generateRandomMapTemplate() {
        return randomGeneratorService.generateMap();
    }

    @Override
    public StageTemplate getMapTemplateByLevelNumber(Integer levelNumber) {
        return Level.getStageTemplate(levelNumber);
    }

    @Override
    public Stage createStageByTemplate(StageTemplate template){
        List<StageCell> stageCells = Arrays.stream(template.getContentTypes()).map(this::transformToStageCell).collect(Collectors.toList());
        return new Stage(stageCells, template.getNumber(), template.getDescription());
    }

    private StageCell transformToStageCell(ContentType contentType) {
        switch (contentType) {
            case EMPTY:
                return new StageCell(new Empty());
            case ENEMY:
                return new StageCell(new Enemy());
            case SPIKE:
                return new StageCell(new Spike());
            case HERO:
                return new StageCell(new Hero());
            default:
                return new StageCell(null);
        }
    }
}
