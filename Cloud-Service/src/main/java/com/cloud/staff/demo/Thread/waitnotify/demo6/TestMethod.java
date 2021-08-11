package com.cloud.staff.demo.Thread.waitnotify.demo6;

public class TestMethod {

    public void waitMethod(Object object) throws InterruptedException {
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+" 时间"+System.currentTimeMillis()+" wait 开始");
            object.wait();
            System.out.println(Thread.currentThread().getName()+" 时间"+System.currentTimeMillis()+" wait 结束");
        }

    }

    public void notifyMtehod(Object object) throws InterruptedException {
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+" 时间"+System.currentTimeMillis()+" notify 开始");
            object.notify();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+" 时间"+System.currentTimeMillis()+" notify 结束");
        }
    }
}
