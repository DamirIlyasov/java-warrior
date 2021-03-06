package ru.itis.javawarrior.service.impl;

import net.openhft.compiler.CompilerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javawarrior.db.service.UserService;
import ru.itis.javawarrior.dto.GameResult;
import ru.itis.javawarrior.service.CompileService;
import ru.itis.javawarrior.util.compile.CompileParts;
import ru.itis.javawarrior.util.compile.Runner;


@Service
public class CompileServiceImpl implements CompileService {
    private int operationNumber = 0;

    private static final String CLASS_NAME = "ru.itis.javawarrior.util.compile.CompiledClass";
    private static final String COMPILED_CLASS = "CompiledClass";

    @Autowired
    private UserService userService;

    @Override
    public GameResult compile(String inputCode) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String className = CLASS_NAME + operationNumber;
        GameResult response = null;
        Runner runner;
        String classCode = CompileParts.BEGINNING_OF_CODE_1_PART +
                //TODO: think about it, smells like shit, but works
                COMPILED_CLASS + operationNumber +
                CompileParts.BEGINNING_OF_CODE_2_PART +
                inputCode +
                CompileParts.ENDING_OF_CODE;

        Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, classCode);
        if (aClass != null) {
            runner = (Runner) aClass.newInstance();
            if (runner != null) {
                response = runner.main(userService.getCurrentUser().getMap());
            }
        }

        operationNumber++;
        return response;
    }
}
