package ru.itis.javawarrior.util.compile;

public class CompileParts {
    public static final String BEGINNING_OF_CODE_1_PART = "package ru.itis.javawarrior.util.compile;\n" +
        "\n" +
        "import java.util.List;\n" +
        "\n" +
        "import ru.itis.javawarrior.service.impl.ActionServiceImpl;\n" +
        "import ru.itis.javawarrior.util.ActionEnum;\n" +
        "\n" +
        "\n" +
        "public class ";
    public static final String BEGINING_OF_CODE_2_PART = " implements Runner {\n" +
        "\n" +
        "    private final ActionServiceImpl actionService = new ActionServiceImpl();\n" +
        "\n" +
        "    @Override\n" +
        "    public List<ActionEnum> main() {";

    public static final String ENDING_OF_CODE = "return actionService.responseActions;\n" +
        "    }\n" +
        "\n" +
        "    private void walk() {\n" +
        "        actionService.walk();\n" +
        "    }\n" +
        "\n" +
        "    private void shoot() {\n" +
        "        actionService.shoot();\n" +
        "    }\n" +
        "\n" +
        "    private void jump() {\n" +
        "        actionService.jump();\n" +
        "    }\n" +
        "}";
}
