package com.cloud.staff.apifirst.controller.java.Thread.waitnotify.demo3;

import lombok.SneakyThrows;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        Object object=new Object();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("thread A wait 开始");
                    object.wait();
                    System.out.println("thread A wait 结束");
                }
            }
        },"A").start();

        Thread.sleep(3000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("thread B notify 开始");
                    object.notify();
                    System.out.println("thread B notify 结束");
                }
            }
        },"B").start();

    }
}
