package com.cloud.staff.demo.Thread.join;

/**
 * join(long)
 */
public class demo3 {
    public static void main(String[] args) {
        try {

        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start:"+System.currentTimeMillis());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A");
        threadA.start();
        threadA.join(2000);
        System.out.println("Thread   end:"+System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
