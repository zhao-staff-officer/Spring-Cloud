package com.cloud.staff.apifirst.controller.java.Thread.create;
//实现runnable创建线程
public class ImplementsRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("运行中");
        System.out.println(Thread.currentThread().getName());
    }
}
