package ru.itis.javawarrior.util.compile;

public class CompileParts {
    public static final String BEGINNING_OF_CODE_1_PART = "package ru.itis.javawarrior.util.compile;\n" +
            "\n" +
            "import ru.itis.javawarrior.exception.InvalidActionException;\n" +
            "import ru.itis.javawarrior.exception.StageCompletedException;\n" +
            "\n" +
            "public class ";
    public static final String BEGINNING_OF_CODE_2_PART = " extends AbstractCompiledClass {\n" +
            "    \n" +
            "    @Override\n" +
            "    protected void start() throws InvalidActionException, StageCompletedException {\n";

    public static final String ENDING_OF_CODE = "    }\n" +
            "}\n";
}
