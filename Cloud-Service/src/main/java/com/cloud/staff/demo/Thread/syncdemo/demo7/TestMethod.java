package com.cloud.staff.demo.Thread.syncdemo.demo7;

public class TestMethod {

    synchronized public void test(){
        if(Thread.currentThread().getName().equals("a")){
            System.out.println("CurrentThreadName="+Thread.currentThread().getName()+",BeginTime="+System.currentTimeMillis());
            int i=1;
            while (i==1){
                if((Math.random()+"").substring(0,8).equals("0.123456")){
                    System.out.println("Thread A run time="+System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        }else{
           System.out.println("Thread B run time="+System.currentTimeMillis());
        }
    }
}
