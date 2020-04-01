package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo7;

/**
 * synchornized 出现异常，自动释放锁
 */
public class Main {
    public static void main(String[] args) {
        TestMethod testMethod=new TestMethod();
        //A
        new Thread((new Runnable() {
            @Override
            public void run() {
                testMethod.test();
            }
        }),"a").start();
        //B
        new Thread((new Runnable() {
            @Override
            public void run() {
                testMethod.test();
            }
        }),"b").start();
    }
}
