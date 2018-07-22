package ru.itis.javawarrior.service;

import ru.itis.javawarrior.entity.Stage;
import ru.itis.javawarrior.entity.StageTemplate;

public interface MapService {
    StageTemplate generateRandomMapTemplate();
    StageTemplate getMapTemplateByLevelNumber(Integer levelNumber);

    Stage createStageByTemplate(StageTemplate template);
}
