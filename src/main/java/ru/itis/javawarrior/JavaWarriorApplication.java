package ru.itis.javawarrior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.javawarrior.util.source.CodeStorage;
import ru.itis.javawarrior.util.source.CompilerUtil;

@SpringBootApplication
public class JavaWarriorApplication {

    public static void main(String[] args) {
        CompilerUtil.compile(CodeStorage.CLASS_CODE);
        SpringApplication.run(JavaWarriorApplication.class, args);
    }
}
