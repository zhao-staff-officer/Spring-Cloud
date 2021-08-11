package com.cloud.staff.demo.Thread.syncdemo.demo6;

public class Sub extends Main{
    synchronized public void optSubMethod(){
        try {
            while(i>0){
                i--;
                System.out.println("sub print i ="+i);
                Thread.sleep(100);
                super.optMainMethod();
            }
        }catch (InterruptedException e){
             e.printStackTrace();
        }
    }
}
