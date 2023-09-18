package com.web.D5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordCountGeeX {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个短句：");
        String sentence = scanner.nextLine();
        scanner.close();

        int wordCount = countWords(sentence);
        int uniqueWordCount = countUniqueWords(sentence);

        System.out.println("短句中的单词个数：" + wordCount);
        System.out.println("短句中的不重复单词个数：" + uniqueWordCount);
    }

    private static int countWords(String sentence) {
        String[] words = sentence.split(" ");
        return words.length;
    }

    private static int countUniqueWords(String sentence) {
        String[] words = sentence.split(" ");

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        return uniqueWords.size();
    }
}

