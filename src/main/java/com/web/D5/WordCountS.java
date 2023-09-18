package com.web.D5;

import java.util.StringTokenizer;

public class WordCountS {
    public static void main(String[] args) {
        StringTokenizer stringTokenizer = new StringTokenizer("This is a test string."," |.");
        int count = 0;
        while (stringTokenizer.hasMoreTokens()) {
            count++;
            stringTokenizer.nextToken();
        }
        System.out.println("Count: " + count);
    }
}
