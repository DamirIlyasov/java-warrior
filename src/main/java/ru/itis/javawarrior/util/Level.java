package ru.itis.javawarrior.util;

import lombok.experimental.UtilityClass;
import ru.itis.javawarrior.entity.enums.ContentType;

@UtilityClass
public class Level {

    private static final ContentType[] FIRST_LEVEL_TEMPLATE = new ContentType[]{
            ContentType.EMPTY,
            ContentType.EMPTY,
            ContentType.EMPTY,
            ContentType.EMPTY,
            ContentType.EMPTY
    };

    private static final ContentType[] SECOND_LEVEL_TEMPLATE = new ContentType[]{
            ContentType.EMPTY,
            ContentType.SPIKE,
            ContentType.EMPTY,
            ContentType.EMPTY,
            ContentType.EMPTY
    };

    private static final ContentType[] THIRD_LEVEL_TEMPLATE = new ContentType[]{
            ContentType.EMPTY,
            ContentType.SPIKE,
            ContentType.EMPTY,
            ContentType.ENEMY,
            ContentType.EMPTY
    };

    public static ContentType[] getStageTemplate(Integer stageNumber) {
        switch (stageNumber) {
            case 1:
                return FIRST_LEVEL_TEMPLATE;
            case 2:
                return SECOND_LEVEL_TEMPLATE;
            case 3:
                return THIRD_LEVEL_TEMPLATE;
            default:
                return FIRST_LEVEL_TEMPLATE;
        }
    }


}
