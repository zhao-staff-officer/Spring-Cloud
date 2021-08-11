package com.cloud.staff.demo.Thread.volatiledemo.demo1;

public class Main {
    public static void main(String[] args) {
        PrintString printString=new PrintString();
        printString.printStringMethod();
        System.out.println("我要停止当前线程"+Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
