package com.cloud.staff.apifirst.controller.java.Thread.create;
//继承Thrad创建线程
public class ExtendsThread2 extends Thread{
    @Override
    public void run(){
        for(int i=0;i<10;i++){
            try {
                int time=(int)(Math.random()*1000);
                Thread.sleep(time);
                System.out.println("run="+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
