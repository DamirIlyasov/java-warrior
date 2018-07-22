package ru.itis.javawarrior.service;

import ru.itis.javawarrior.dto.GameResult;

/**
 * @author Damir Ilyasov
 */
public interface CompileService {
    GameResult compile(String code) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
