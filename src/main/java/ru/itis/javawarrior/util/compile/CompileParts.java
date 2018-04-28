package ru.itis.javawarrior.util.compile;

public class CompileParts {
    public static final String BEGINNING_OF_CODE_1_PART = "package ru.itis.javawarrior.util.compile;\n" +
            "\n" +
            "import ru.itis.javawarrior.exception.HeroDiedException;\n" +
            "import ru.itis.javawarrior.exception.StageCompletedException;\n" +
            "import ru.itis.javawarrior.exception.TimeOutException;\n" +
            "\n" +
            "public class ";
    public static final String BEGINNING_OF_CODE_2_PART = " extends AbstractCompiledClass {\n" +
            "    \n" +
            "    @Override\n" +
            "    protected void start() throws TimeOutException, StageCompletedException, HeroDiedException {\n" +
            "        for (int i = 0; i < 100; i++) {\n" +
            "           ";

    public static final String ENDING_OF_CODE = "\n" +
            "       }\n" +
            "    }\n" +
            "}\n";
}
