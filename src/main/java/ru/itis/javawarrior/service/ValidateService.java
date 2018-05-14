package ru.itis.javawarrior.service;

import ru.itis.javawarrior.util.ban.Validation;

/**
 * @author Damir Ilyasov
 */
public interface ValidateService {
    Validation validate(String code);
}
