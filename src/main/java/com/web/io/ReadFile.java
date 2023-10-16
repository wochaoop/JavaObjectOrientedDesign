package com.web.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    private static final Logger logger = LogManager.getLogger(ReadFile.class);

    public static void main(String[] args) {
        String fileName = "output.txt";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = safeReadLine(bufferedReader)) != null) {
                // 处理每行数据
                processLine(line);
            }

            // 关闭文件流
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            logger.error("Error reading file: {}", e.getMessage());
        }
    }

    private static String safeReadLine(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            logger.error("Error reading line from file: {}", e.getMessage());
            throw new RuntimeException("Error reading line from file", e);
        }
    }

    private static void processLine(String line) {
        // 在这里处理每行数据的逻辑
        System.out.println(line); // 这里仅简单地将每行数据打印到控制台
    }
}
