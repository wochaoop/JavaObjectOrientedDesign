package com.web;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        // 创建一个Scanner对象，用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 创建一个Map对象，用于存储单词及其出现次数
        Map<String, Integer> wordCount = new HashMap<>();

        // 提示用户输入一个句子
        System.out.println("请输入一个句子：");
        String input = scanner.nextLine();

        // 对输入的句子进行分词处理
        String[] words = input.split(" ");

        // 遍历单词数组，将每个单词添加到Map对象中
        for (String word : words) {
            word = word.trim(); // 去除单词前的空格
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        // 输出单词及其出现次数
        System.out.println("单词及其出现次数：");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // 关闭Scanner对象
        scanner.close();
    }
}

