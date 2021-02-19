package com.cloud.staff.apifirst.controller.java.Thread.waitnotify.demo2;

public class AdminTest2 {
    public static void main(String[] args) {
        try {
            String lock = new String("");
            System.out.println("asy上面");
            synchronized (lock) {
                System.out.println("asy第一行");
                lock.wait();
                System.out.println("wait下面代码");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
