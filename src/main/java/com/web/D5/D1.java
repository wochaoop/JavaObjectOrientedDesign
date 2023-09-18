package com.web.D5;

// 统计短句中单词的个数
// 统计短句中不重复单词个数
public class D1 {
    public static void main(String[] args) {
//        String st001="How dear your mother is,so why do you care?";
//        StringBuffer stringBuffer = new StringBuffer(st001);
//        String st002 = "This is st2 test!";
//        System.out.println(st001.toUpperCase(Locale.ROOT));
//        StringBuffer st003 = new StringBuffer(st001);
//        System.out.println(st003.append(st002));
//        System.out.println(st003.reverse());

//        System.out.println(stringBuffer);
//        StringTokenizer stringTokenizer = new StringTokenizer(st001,);
//        System.out.println("你妈了个筹备");
//        while (stringTokenizer.hasMoreTokens()){
//            System.out.println(stringTokenizer.nextToken());
                String sentence = "Hello, how are you?";
                int wordCount = sentence.split(" ").length;
                System.out.println("本句共有 " + wordCount + " 个单词。");
            }
    }
