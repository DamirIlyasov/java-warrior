package ru.itis.javawarrior.service.impl;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.service.Actions;
import ru.itis.javawarrior.service.ValidateService;

/**
 * @author Damir Ilyasov
 */
@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean validate(String code) {
        for (Method method : Actions.class.getMethods()) {
            code = code.replaceAll(method.getName() + ".*?;", "");
        }
        code = code.trim();
        return code.equals("");
    }
}
