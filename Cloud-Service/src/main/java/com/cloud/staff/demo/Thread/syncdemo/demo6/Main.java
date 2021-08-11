package com.cloud.staff.demo.Thread.syncdemo.demo6;

public class Main {
    public  int i=10;
    synchronized public void optMainMethod(){
        try{
            i--;
            System.out.println("main print i="+i);
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
