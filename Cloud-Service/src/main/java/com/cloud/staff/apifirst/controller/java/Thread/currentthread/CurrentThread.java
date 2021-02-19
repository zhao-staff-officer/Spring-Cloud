package com.cloud.staff.apifirst.controller.java.Thread.currentthread;
//获取当前线程信息
public class CurrentThread implements  Runnable{
    public  CurrentThread(){
        System.out.println("构造方法"+Thread.currentThread().getName());
    }

    @Override
    public void run() {
        System.out.println("run方法"+Thread.currentThread().getName());
    }
}
