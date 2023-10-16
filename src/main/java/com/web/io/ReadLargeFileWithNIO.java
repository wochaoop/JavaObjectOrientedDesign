package com.web.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadLargeFileWithNIO {

    private static final Logger logger = LogManager.getLogger(ReadFile.class);
    public static void main(String[] args) {
        String fileName = "output.txt";

        try (Stream<String> lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            lines.forEach(ReadLargeFileWithNIO::processLine);
        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
        }
    }

    private static void processLine(String line) {
        // 在这里处理每行数据的逻辑
        System.out.println(line); // 这里仅简单地将每行数据打印到控制台
    }
}
