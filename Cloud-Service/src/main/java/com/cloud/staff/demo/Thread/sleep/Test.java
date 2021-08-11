package com.cloud.staff.demo.Thread.sleep;

public class Test {
    public static void main(String[] args) {
       ThreadSleep ts=new ThreadSleep();
       Thread thread=new Thread(ts,"threadName");
       System.out.println("start:="+System.currentTimeMillis());
       thread.start();
       System.out.println("end:="+System.currentTimeMillis());
    }
}
