package com.cloud.staff.apifirst.controller.java.Thread.lock.reentrantreadwritelock;

/**
 * ReentrantReadWriteLock读读共享
 */
public class demotest {
    public static void main(String[] args) {
        demo2 demo2 = new demo2();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.readMethod();
            }
        },"A");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo2.readMethod();
            }
        },"B");

        thread1.start();
        thread2.start();
    }

}
