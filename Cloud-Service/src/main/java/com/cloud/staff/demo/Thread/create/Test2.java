package com.cloud.staff.demo.Thread.create;

public class Test2 {
    public static void main(String[] args) {
        ExtendsThread2 ex = new ExtendsThread2();
        ex.setName("mythread");
        ex.start();
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("Main=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
