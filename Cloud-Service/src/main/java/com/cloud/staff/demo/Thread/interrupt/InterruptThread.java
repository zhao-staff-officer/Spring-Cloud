package com.cloud.staff.demo.Thread.interrupt;

public class InterruptThread extends Thread{
    @Override
    public void run(){
        super.run();
        for(int i =0 ;i<500000;i++){
            System.out.println("i="+(i+1));
        }
    }
}
