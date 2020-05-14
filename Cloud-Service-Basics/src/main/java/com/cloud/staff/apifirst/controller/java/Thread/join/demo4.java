package com.cloud.staff.apifirst.controller.java.Thread.join;

import lombok.SneakyThrows;

/**
 * join()先行运行
 */
@Deprecated
public class demo4 {
    public static void main(String[] args) {
        System.out.println("star:"+System.currentTimeMillis());
        try{
            Thread threadB=new Thread(new Runnable() {
                @Override
                synchronized  public void run() {
                   System.out.println("current_thread Sta:"+Thread.currentThread().getName()+",time:"+System.currentTimeMillis());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("current_thread End:"+Thread.currentThread().getName()+",time:"+System.currentTimeMillis());
                }
            },"B");

            Thread threadA=new Thread(new Runnable() {
                @Override
                public void run() {
                     synchronized (threadB){
                         System.out.println("current_thread Sta:"+Thread.currentThread().getName()+",time:"+System.currentTimeMillis());
                         try {
                             Thread.sleep(5000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                         System.out.println("current_thread End:"+Thread.currentThread().getName()+",time:"+System.currentTimeMillis());
                     }
                }
            },"A");

            threadA.start();
            threadB.start();
            threadB.join();
            System.out.println("current_thread End:"+Thread.currentThread().getName()+",time:"+System.currentTimeMillis());
            System.out.println("End:"+System.currentTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
