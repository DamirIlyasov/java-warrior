package ru.itis.javawarrior.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.service.Actions;
import ru.itis.javawarrior.service.ValidateService;
import ru.itis.javawarrior.util.ban.Validation;

import java.lang.reflect.Method;

/**
 * @author Damir Ilyasov
 */
@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public Validation validate(String code) {
        for (Method method : Actions.class.getMethods()) {
            code = code.replaceAll(method.getName() + ".*?;", "");
        }
        code = code.trim();
        return new Validation(code.equals(""), "");
    }
}
