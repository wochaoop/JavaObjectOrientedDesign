package com.web.D5;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class WordCountS {
    public static void main(String[] args) {
//        分割商法
        StringTokenizer stringTokenizer = new StringTokenizer("This is a test string.So this is test text"," |.");
//        下面是统计短句中不重复单词个数
        Set<String> set = new HashSet<>();
        while (stringTokenizer.hasMoreTokens()){
            set.add(stringTokenizer.nextToken());
        }
        System.out.println(set.size());
        String st001 = "This is a test string.So this is test text";
        Set<Character> set1 = new HashSet<>();
        for (int i = 0; i < st001.length(); i++) {
            set1.add(st001.charAt(i));
        }
        System.out.println(set1.size());

//        别看了 下面是统计短句中单词的个数
//        int count = 0;
//        while (stringTokenizer.hasMoreTokens()) {
//            count++;
//            stringTokenizer.nextToken();
//        }
//        System.out.println("Count: " + count);
    }
}
