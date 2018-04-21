package ru.itis.javawarrior.util.source;

import net.openhft.compiler.CompilerUtils;
import ru.itis.javawarrior.util.test.ControlInteface;


public class CompilerUtil {

    public static void compile(String javaCode) {
        String className = "ru.itis.javawarrior.util.test.Test";
        Class aClass = null;
        try {
            aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(className, javaCode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ControlInteface runner = null;
        try {
            if (aClass != null) {
                runner = (ControlInteface) aClass.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (runner != null) {
            runner.run();
        }
    }
}
