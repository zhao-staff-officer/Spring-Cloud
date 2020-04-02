package com.cloud.staff.apifirst.controller.java.Thread.volatiledemo.demo2;

public class Main {
    public static void main(String[] args) {
        PrintString printString=new PrintString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printString.printStringMethod();
            }
        }).start();
        System.out.println("我要停止当前线程"+Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
