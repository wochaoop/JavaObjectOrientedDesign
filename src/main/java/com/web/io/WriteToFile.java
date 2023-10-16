package com.web.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class WriteToFile {
    private static final Logger logger = Logger.getLogger(WriteToFile.class.getName());

    public static void main(String[] args) {
        String data = "Hello, World!";
        String filePath = "output.txt";

        try {
            // 将字符串转换为字节数组

            // 使用Files.write()方法写入文件，如果文件不存在则创建新文件
            Path path = Path.of(filePath);
            Files.writeString(path, data, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

            logger.info("Data has been written to the file: " + filePath);
        } catch (IOException e) {
            // 记录错误日志
            logger.severe("Error writing to the file: " + e.getMessage());
        }
    }
}
