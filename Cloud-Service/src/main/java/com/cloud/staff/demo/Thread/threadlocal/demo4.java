package com.cloud.staff.demo.Thread.threadlocal;

/**
 * threadlocal 初始化值 线程隔离
 */
public class demo4 {

    static class ThreadlocalExtends extends ThreadLocal{

        @Override
        protected Object initialValue(){
            return System.currentTimeMillis();
        }
    }

    public static ThreadlocalExtends threadlocalExtends=new ThreadlocalExtends();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<100;i++){
                    System.out.println(Thread.currentThread().getName()+":"+threadlocalExtends.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"A").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<100;i++){
                    System.out.println(Thread.currentThread().getName()+":"+threadlocalExtends.get());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"B").start();


    }
}
