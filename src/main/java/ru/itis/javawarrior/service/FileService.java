package ru.itis.javawarrior.service;

import java.io.IOException;

public interface FileService {
    String TEST_FILE_PATH = "TestFile.java";
    String TEST_FILE_NAME = "TestFile";
    void writeToFile(String code) throws IOException;
}
