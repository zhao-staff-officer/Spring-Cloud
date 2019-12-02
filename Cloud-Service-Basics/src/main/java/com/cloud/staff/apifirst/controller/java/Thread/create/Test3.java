package com.cloud.staff.apifirst.controller.java.Thread.create;

public class Test3 {
    public static void main(String[] args) {
        ImplementsRunnable ir=new ImplementsRunnable();
        Thread thread=new Thread(ir,"threadname");
        thread.start();
        System.out.println("运行结束");
    }
}
