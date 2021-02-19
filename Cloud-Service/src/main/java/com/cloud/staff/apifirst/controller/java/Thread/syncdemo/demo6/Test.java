package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo6;

/**
 * 重入锁继承性
 */
public class Test {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub=new Sub();
                sub.optSubMethod();
            }
        }).start();
    }
}
