package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo9;

/**
 * synchornized
 * String 类型
 */
public class Main {
    public static void main(String[] args) {
        TestMethod testMethod=new TestMethod();
        //A
        new Thread((new Runnable() {
            @Override
            public void run() {
                testMethod.print("AA");
            }
        }),"A").start();

        //B
        new Thread((new Runnable() {
            @Override
            public void run() {
                testMethod.print("AA");
            }
        }),"B").start();
    }
}
