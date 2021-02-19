package com.cloud.staff.apifirst.controller.java.Thread.lock.reentrantlock;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ReentrantLocak使用
 */
public class demo1 {

    static class LockThread {
        private Lock lock = new ReentrantLock();

        public void testLock() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "打印：" + i);
                }
            }finally {
                lock.unlock();
            }


        }
    }

    public static void main(String[] args) {
        LockThread lockThread=new LockThread();
       new Thread(new Runnable() {
           @Override
           public void run() {
               lockThread.testLock();
           }
       },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockThread.testLock();
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockThread.testLock();
            }
        },"C").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockThread.testLock();
            }
        },"D").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lockThread.testLock();
            }
        },"E").start();
    }
}
