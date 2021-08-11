package com.cloud.staff.demo.Thread.waitnotify.demo6;

import lombok.SneakyThrows;

/**
 * notify 唤醒线程不释放锁
 * 等synchornized修饰块执行完毕
 */
public class MainTest {
    public static void main(String[] args) {
        TestMethod testMethod=new TestMethod();
        Object object=new Object();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                testMethod.waitMethod(object);
                testMethod.notifyMtehod(object);
            }
        },"A").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                testMethod.notifyMtehod(object);
            }
        },"B").start();
    }
}
