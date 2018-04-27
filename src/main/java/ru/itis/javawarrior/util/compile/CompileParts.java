package ru.itis.javawarrior.util.compile;

public class CompileParts {
    public static final String BEGINNING_OF_CODE_1_PART = "package ru.itis.javawarrior.util.compile;\n" +
        "\n" +
        "import ru.itis.javawarrior.entity.GameResult;\n" +
        "import ru.itis.javawarrior.exception.InvalidActionException;\n" +
        "import ru.itis.javawarrior.exception.StageCompletedException;\n" +
        "import ru.itis.javawarrior.service.impl.ActionServiceImpl;\n" +
        "\n" +
        "\n" +
        "public class ";
    public static final String BEGINING_OF_CODE_2_PART = " implements Runner {\n" +
        "\n" +
        "    private final ActionServiceImpl actionService = new ActionServiceImpl();\n" +
        "\n" +
        "    @Override\n" +
        "    public GameResult main() {\n" +
        "        try {";

    public static final String ENDING_OF_CODE = "}\n" +
        "        catch (InvalidActionException actionException) {\n" +
        "            return new GameResult(actionService.getResponseActions(), false);\n" +
        "        }\n" +
        "        catch (StageCompletedException stageCompleteExeption) {\n" +
        "            return new GameResult(actionService.getResponseActions(), true);\n" +
        "        }\n" +
        "        // stage failed if StageCompleteException wasn't caught\n" +
        "        return new GameResult(actionService.getResponseActions(), false);\n" +
        "    }\n" +
        "\n" +
        "    private void walk() {\n" +
        "        actionService.walk();\n" +
        "    }\n" +
        "\n" +
        "    private void attack() {\n" +
        "        actionService.attack();\n" +
        "    }\n" +
        "\n" +
        "    private void jump() {\n" +
        "        actionService.jump();\n" +
        "    }\n" +
        "}\n";
}
