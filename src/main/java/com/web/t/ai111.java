package com.web.t;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ai111 {
//    写一个文件缓冲流的代码，用于读取文件
public static void main(String[] args) {
    // 读取文件
    String fileName = "output.txt";
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            // 在这里处理每行数据的逻辑
            System.out.println(line); // 这里仅简单地将每行数据打印到控制台
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}

}
