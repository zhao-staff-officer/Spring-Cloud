package com.cloud.staff.apifirst.controller.java.Thread.interrupt;

public class InterruptedThread extends Thread {
    @Override
    public void run(){
        super.run();
        for(int i =0 ;i<50000;i++){
            System.out.println("i="+(i+1));
        }
    }
}
