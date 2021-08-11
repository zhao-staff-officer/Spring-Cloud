package com.cloud.staff.demo.Thread.volatiledemo.demo2;

/**
 * volatile
 * 1：volatile是线程同步轻量级实现，volatile只能修饰变量，synchronized可以修饰变量/方法/代码块。
 * 2: volatile不会线程阻塞，synchronized会线程阻塞。
 * 3：volatile解决线程变量可见性，synchronized解决线程资源访问同步性。
 */
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
