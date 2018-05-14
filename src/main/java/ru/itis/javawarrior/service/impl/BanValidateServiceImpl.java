package ru.itis.javawarrior.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javawarrior.exception.ServerException;
import ru.itis.javawarrior.service.FileService;
import ru.itis.javawarrior.service.ValidateService;
import ru.itis.javawarrior.util.ban.BannedConstructions;
import ru.itis.javawarrior.util.ban.Validation;
import ru.itis.javawarrior.util.messages.Messages;

import javax.tools.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class BanValidateServiceImpl implements ValidateService {

    @Autowired
    private FileService fileService;

    @Override
    public Validation validate(String code) {
        return performValidationProcess(code);
    }

    private Validation performValidationProcess(String code) {
        return checkSyntax(code);
    }

    private Validation checkSyntax(String code) {

        try {
            fileService.writeToFile(code);
        } catch (IOException e) {
            throw new ServerException(Messages.VALIDATION_ERROR);
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnits =
                fileManager.getJavaFileObjectsFromStrings(Arrays.asList(fileService.TEST_FILE_PATH));

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiler.getTask(null, fileManager, diagnostics, null, null, compilationUnits).call();

        List<String> messages = new ArrayList<String>();

        for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {

            if (diagnostic.getLineNumber() == 6 && diagnostic.getPosition() == 174) {
                continue; // не удалять, это чтобы избежать ошибки из-за lombok (по-ругому никак)
            }
            messages.add(diagnostic.getKind() + ": Line [" + (diagnostic.getLineNumber() - 10)
                    + "] Position [" + (diagnostic.getPosition() - 325)
                    + "] " + diagnostic.getMessage(Locale.ROOT) + "\n");
        }


        if (messages.isEmpty()) {
            return checkSecurity(code);
        } else {
            return new Validation(false, Messages.INVALID_CODE + messages);
        }
    }

    private Validation checkSecurity(String code) {
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
