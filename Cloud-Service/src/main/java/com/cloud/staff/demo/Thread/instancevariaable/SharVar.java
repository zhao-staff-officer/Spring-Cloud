package com.cloud.staff.demo.Thread.instancevariaable;
//共享变量
public class SharVar implements Runnable {
    private  int count = 5;
    @Override
    synchronized  public void run() {
        count -- ;
        System.out.println("当前线程时间"+System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName()+"当前值"+count);
    }
}
