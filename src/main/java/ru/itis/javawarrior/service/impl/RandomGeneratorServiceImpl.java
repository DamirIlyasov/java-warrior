package ru.itis.javawarrior.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.entity.StageTemplate;
import ru.itis.javawarrior.entity.enums.ContentType;
import ru.itis.javawarrior.service.RandomGeneratorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    private List<ContentType> tiles;
    private static final int DEFAULT_LEVEL_LENGTH = 5;

    @Override
    public StageTemplate generateMap(int number) {
        tiles = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            addTile(i);
            System.out.println(i + " = " + tiles.get(i));
        }
        StageTemplate template = new StageTemplate();
        template.setNumber(number);
        template.setDescription(getDescription());
        template.setContentTypes(tiles.toArray(new ContentType[0]));
        System.out.println(getDescription());
        return template;
    }

    @Override
    public StageTemplate generateMap() {
        return generateMap(DEFAULT_LEVEL_LENGTH);
    }

    private void addTile(int tileNumber) {
        if (tileNumber == 0) {
            tiles.add(ContentType.HERO);
        } else {
            ContentType previosTile = tiles.get(tiles.size() - 1);
            List<ContentType> allowedTiles = new ArrayList<>();
            switch (previosTile) {
                case HERO:
                case EMPTY:
                    allowedTiles.add(ContentType.EMPTY);
                    allowedTiles.add(ContentType.ENEMY);
                    allowedTiles.add(ContentType.SPIKE);
                    break;
                case SPIKE:
                    allowedTiles.add(ContentType.EMPTY);
                    break;
                case ENEMY:
                    if (tileNumber > 1) {
                        ContentType prePreviosTile = tiles.get(tiles.size() - 2);
                        if (prePreviosTile == ContentType.ENEMY) {
                            allowedTiles.add(ContentType.EMPTY);
                        } else {
                            allowedTiles.add(ContentType.ENEMY);
                            allowedTiles.add(ContentType.EMPTY);
                        }
                    } else {
                        allowedTiles.add(ContentType.ENEMY);
                        allowedTiles.add(ContentType.EMPTY);
                    }
            }
            tiles.add(generateRandomTile(allowedTiles));
        }
    }

    private ContentType generateRandomTile(List<ContentType> allowedTiles) {
        if (allowedTiles.size() == 0) {
            return ContentType.EMPTY;
        }
        Random random = new Random();
        return allowedTiles.get(random.nextInt(allowedTiles.size()));
    }

    private String getDescription(){
        if (tiles.contains(ContentType.SPIKE)  && tiles.contains(ContentType.ENEMY)){
            return "Будь осторожен! Подозреваемые используют колючую проволоку!";
        } else if (tiles.contains(ContentType.SPIKE)){
            return "Кто-то раскидал колючую проволоку на пути к цели, будь аккуратен.";
        } else if (tiles.contains(ContentType.ENEMY)){
            return "А вот и подозреваемые, СТРЕЛЯТЬ НА ПОРАЖЕНИЕ!!!";
        } else {
            return "Ого, абсолютно чисто... Ну, будем считать что тебе повезло.";
        }
    }
}
