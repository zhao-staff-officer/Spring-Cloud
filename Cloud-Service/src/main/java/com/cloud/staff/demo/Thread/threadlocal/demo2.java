package com.cloud.staff.demo.Thread.threadlocal;

/**
 * 验证thradlocal线程隔离性
 */
public class demo2 {

    public static ThreadLocal threadLocal=new ThreadLocal();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                      threadLocal.set("threadA set value:"+(i+1));
                      System.out.println(threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    threadLocal.set("threadB set value:"+(i+1));
                    System.out.println(threadLocal.get());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"B").start();

        for(int i=0;i<100;i++){
            threadLocal.set("Main set value:"+(i+1));
            System.out.println(threadLocal.get());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
