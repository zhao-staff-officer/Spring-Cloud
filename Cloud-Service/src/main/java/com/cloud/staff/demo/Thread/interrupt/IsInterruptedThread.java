package com.cloud.staff.demo.Thread.interrupt;

public class IsInterruptedThread extends  Thread{
    @Override
    public void run(){
        super.run();
        System.out.println("start"+System.currentTimeMillis());
        for(int i =0 ;i<50000;i++){
            System.out.println("i="+(i+1));
        }
        System.out.println("end"+System.currentTimeMillis());
    }
}
