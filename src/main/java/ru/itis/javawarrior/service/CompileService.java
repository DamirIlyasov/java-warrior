package ru.itis.javawarrior.service;

import java.util.List;

import ru.itis.javawarrior.entity.GameResult;
import ru.itis.javawarrior.util.ActionEnum;

/**
 * @author Damir Ilyasov
 */
public interface CompileService {
    GameResult compile(String code) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
