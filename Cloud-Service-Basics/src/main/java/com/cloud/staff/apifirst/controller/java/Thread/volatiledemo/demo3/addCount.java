package com.cloud.staff.apifirst.controller.java.Thread.volatiledemo.demo3;

public class addCount extends Thread{

    volatile private static int count;

    public void addCountNum(){
        for(int i=0;i<100;i++){
            count++;
        }
        System.out.println("count ="+count);
    }

    @Override
    public void run(){
        addCountNum();
    }
}
