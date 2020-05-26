package com.cloud.staff.apifirst.controller.java.Thread.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock公平锁与非公平锁
 */
public class demo4 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        demo41 threadDemo = new demo41(lock);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "运行了");
                threadDemo.service();
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
