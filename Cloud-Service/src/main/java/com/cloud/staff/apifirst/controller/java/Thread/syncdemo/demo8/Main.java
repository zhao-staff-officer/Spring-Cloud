package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo8;

/**
 * 同步块示例
 * synchornzied 修饰块是同步执行
 */
public class Main {
    public static void main(String[] args) {
        TestMethod testMethod=new TestMethod();
        final Long[] threadTime = new Long[4];
        //A
        new Thread((new Runnable() {
            @Override
            public void run() {
                threadTime[0] =System.currentTimeMillis();
                testMethod.doLongTimeTask();
                threadTime[1] =System.currentTimeMillis();
            }
        }),"a").start();
        //b
        new Thread((new Runnable() {
            @Override
            public void run() {
                threadTime[2] =System.currentTimeMillis();
                testMethod.doLongTimeTask();
                threadTime[3] =System.currentTimeMillis();
            }
        }),"b").start();
        try {
            Thread.sleep(10000);
            System.out.println("总计耗时="+(threadTime[3]-threadTime[0])/100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
