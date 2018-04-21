package ru.itis.javawarrior.service;

import java.util.List;

import ru.itis.javawarrior.util.ActionEnum;

/**
 * @author Damir Ilyasov
 */
public interface CompileService {
    List<ActionEnum> compile(String code);
}
