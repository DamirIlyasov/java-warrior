package ru.itis.javawarrior.service.impl;

import java.util.List;

import net.openhft.compiler.CompilerUtils;
import org.springframework.stereotype.Service;
import ru.itis.javawarrior.service.CompileService;
import ru.itis.javawarrior.util.ActionEnum;
import ru.itis.javawarrior.util.compile.CompileParts;
import ru.itis.javawarrior.util.compile.Runner;


@Service
public class CompileServiceImpl implements CompileService {
    @Override
    public List<ActionEnum> compile(String inputCode) {
        String className = "ru.itis.javawarrior.util.compile.CompiledClass";
        List<ActionEnum> response = null;
        Runner runner;
        String classCode = CompileParts.BEGINNING_OF_CODE +
            inputCode +
            CompileParts.ENDING_OF_CODE;
        try {
            Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, classCode);
            if (aClass != null) {
                runner = (Runner) aClass.newInstance();
                if (runner != null) {
                    response = runner.main();
                }
            }
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException ignored) {

        }
        return response;
    }
}
