package com.cloud.staff.apifirst.controller.java.Thread.sleep;

public class ThreadSleep implements  Runnable{
    @Override
    public void run() {
        System.out.println("run threadName="+Thread.currentThread().getName()+"begin");
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("run threadName="+Thread.currentThread().getName()+"end");
    }
}
