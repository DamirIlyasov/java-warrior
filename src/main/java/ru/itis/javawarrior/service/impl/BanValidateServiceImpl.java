package ru.itis.javawarrior.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.exception.ServerException;
import ru.itis.javawarrior.service.ValidateService;
import ru.itis.javawarrior.util.ban.BannedConstructions;
import ru.itis.javawarrior.util.ban.Validation;
import ru.itis.javawarrior.util.messages.Messages;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BanValidateServiceImpl implements ValidateService {

    @Override
    public Validation validate(String code) {
        String regex;
        List<Field> fields = Arrays.asList(BannedConstructions.class.getFields());
        List<String> bannedConstructions = fields
                .stream()
                .map(this::getString)
                .collect(Collectors.toList());
        for (String bannedConstruction : bannedConstructions) {
            regex = ".*" + bannedConstruction + ".*";
            if (code.matches(regex)) {
                return new Validation(false, Messages.UNACCEPTABLE_CODE + " : " + bannedConstruction);
            }
        }
        return new Validation(true, Messages.VALIDATION_SUCCESS);
    }

    private String getString(Field field) {
        try {
            return (String) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ServerException(Messages.VALIDATION_ERROR);
        }
    }

}
