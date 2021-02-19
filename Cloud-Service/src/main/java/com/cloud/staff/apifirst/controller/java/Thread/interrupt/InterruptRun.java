package com.cloud.staff.apifirst.controller.java.Thread.interrupt;

public class InterruptRun {
    public static void main(String[] args) {
        try{
            InterruptThread it=new InterruptThread();
            it.start();
            Thread.sleep(2000);
            it.interrupt();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
