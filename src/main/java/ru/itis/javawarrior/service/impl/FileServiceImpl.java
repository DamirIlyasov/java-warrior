package ru.itis.javawarrior.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.javawarrior.service.FileService;
import ru.itis.javawarrior.util.compile.CompileParts;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public void writeToFile(String code) throws IOException {
        List<String> lines = Arrays.asList(
                CompileParts.BEGINNING_OF_CODE_1_PART +
                        TEST_FILE_NAME +
                CompileParts.BEGINNING_OF_CODE_2_PART,
                code,
                CompileParts.ENDING_OF_CODE);
        Path file = Paths.get(TEST_FILE_PATH);
        Files.write(file, lines, Charset.forName("UTF-8"));
    }
}
