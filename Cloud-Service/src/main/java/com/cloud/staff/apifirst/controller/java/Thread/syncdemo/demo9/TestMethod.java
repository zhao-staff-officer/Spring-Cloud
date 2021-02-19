package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo9;


public class TestMethod {
    public void print(java.lang.String pararm){
        try {
        synchronized (pararm){
            while(true){
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10000);
             }
          }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
