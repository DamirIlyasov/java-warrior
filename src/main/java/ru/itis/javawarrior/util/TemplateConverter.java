package ru.itis.javawarrior.util;

import ru.itis.javawarrior.entity.StageTemplate;
import ru.itis.javawarrior.entity.enums.ContentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TemplateConverter implements AttributeConverter<StageTemplate, String> {

    private static final String SEPARATOR = "@";
    private static final String EMPTY_CONTENT = "|";

    @Override
    public String convertToDatabaseColumn(StageTemplate template) {
        if (template == null)
            return "";
        StringBuilder builder = new StringBuilder();
        builder.append(template.getNumber()).append(SEPARATOR);
        if (template.getContentTypes() != null) {
            for (int i = 0; i < template.getContentTypes().length; i++) {
                builder.append(template.getContentTypes()[i]).append(SEPARATOR);
            }
        } else {
            builder.append(EMPTY_CONTENT).append(SEPARATOR);
        }
        builder.append(template.getDescription()).append(SEPARATOR);
        return builder.toString();
    }

    @Override
    public StageTemplate convertToEntityAttribute(String string) {
        if (string.equals(""))
            return new StageTemplate();
        StageTemplate template = new StageTemplate();
        String[] data = string.split(SEPARATOR);
        template.setNumber(Integer.parseInt(data[0]));
        ContentType[] contentTypes = new ContentType[5];
        if (!data[1].equals(EMPTY_CONTENT)) {
            for (int i = 1; i < 6; i++) {
                switch (data[i]) {
                    case "HERO":
                        contentTypes[i - 1] = ContentType.HERO;
                        break;

                    case "EMPTY":
                        contentTypes[i - 1] = ContentType.EMPTY;
                        break;

                    case "ENEMY":
                        contentTypes[i - 1] = ContentType.ENEMY;
                        break;

                    case "SPIKE":
                        contentTypes[i - 1] = ContentType.SPIKE;
                        break;
                }
            }
        }
        template.setDescription(data[data.length - 1]);
        template.setContentTypes(contentTypes);
        return template;
    }
}
