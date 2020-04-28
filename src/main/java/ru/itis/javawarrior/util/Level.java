package ru.itis.javawarrior.util;

import lombok.experimental.UtilityClass;
import ru.itis.javawarrior.entity.StageTemplate;
import ru.itis.javawarrior.entity.enums.ContentType;

@UtilityClass
public class Level {

    private static final ContentType[] FIRST_LEVEL_TEMPLATE = new ContentType[]{
            ContentType.HERO,
            ContentType.EMPTY,
            ContentType.EMPTY,
            ContentType.EMPTY,
            ContentType.EMPTY
    };

    private static final ContentType[] SECOND_LEVEL_TEMPLATE = new ContentType[]{
            ContentType.HERO,
            ContentType.SPIKE,
            ContentType.EMPTY,
            ContentType.EMPTY,
            ContentType.EMPTY
    };

    private static final ContentType[] THIRD_LEVEL_TEMPLATE = new ContentType[]{
            ContentType.HERO,
            ContentType.SPIKE,
            ContentType.EMPTY,
            ContentType.ENEMY,
            ContentType.EMPTY
    };

    public static StageTemplate getStageTemplate(Integer stageNumber) {
        StageTemplate template = new StageTemplate();
        switch (stageNumber) {
            case 2:
                template.setNumber(2);
                template.setContentTypes(SECOND_LEVEL_TEMPLATE);
                template.setDescription("Похоже, местная шпана раскидала колючую проволоку. Смотри под ноги!");
                break;
            case 3:
                template.setNumber(3);
                template.setContentTypes(THIRD_LEVEL_TEMPLATE);
                template.setDescription("Что ж, первая серьезная мисссия, салага. Необходимо попасть внутрь небольшого наркопритона в черте города для нахождения улик и зацепок. Попасть внутрь решается через запасной ход. Удачи!");
                break;
            default:
                template.setNumber(1);
                template.setContentTypes(FIRST_LEVEL_TEMPLATE);
                template.setDescription("Твоя первая миссия, новобранец. Тебе повезло, сегодня без неприятностей, просто патруль.");
        }
        return template;
    }


}
