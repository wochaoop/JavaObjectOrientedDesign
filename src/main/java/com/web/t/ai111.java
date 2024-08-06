package com.web.t;

public class ai111 {
//    写一个文件缓冲流的代码，用于读取文件
public static void main(String[] args) {
    int a=1;
    // \u000d \u0061\u002b\u002b\u003b
    System.out.println(a);

    int x = 0x7fffffff;

    judge2(x);
}

    public static void judge2(int x){
        if (x>>>31==0){
            System.out.println("正数");
        }else if (x>>>31==1){
            System.out.println("负数");
        }
    }

}
