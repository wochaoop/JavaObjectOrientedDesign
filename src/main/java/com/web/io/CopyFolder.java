package com.web.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

public class CopyFolder {
    private static final Logger logger = LogManager.getLogger(CopyFolder.class);

    public static void main(String[] args) {
        Path sourceDir = Paths.get("sourceFolder");
        Path targetDir = Paths.get("targetFolder");

        try {
            long startTime = System.currentTimeMillis();
            Files.walkFileTree(sourceDir, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE,
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            Path targetFile = targetDir.resolve(sourceDir.relativize(file));
                            try {
                                Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                                logger.info("Copied file: {}", file);
                            } catch (IOException e) {
                                // 使用日志框架记录错误信息
                                logger.error("Error copying file: {}", file, e);
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) {
                            // 使用日志框架记录错误信息
                            logger.error("Error visiting file: {}", file, exc);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                            Path targetDirToCreate = targetDir.resolve(sourceDir.relativize(dir));
                            try {
                                Files.createDirectories(targetDirToCreate);
                                logger.info("Created directory: {}", dir);
                            } catch (IOException e) {
                                // 使用日志框架记录错误信息
                                logger.error("Error creating directory: {}", dir, e);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    });

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            logger.info("Folder copied successfully. Time taken: {} milliseconds", elapsedTime);
        } catch (IOException e) {
            // 使用日志框架记录顶层异常信息
            logger.error("Error copying folder: {}", e.getMessage(), e);
        }
    }
}
