package com.cloud.staff.apifirst.controller.java.Thread.waitnotify.demo7;

import lombok.SneakyThrows;

/**
 * wait(long)
 * 固定时间唤醒线程
 */
public class waitlong {
    public static void main(String[] args) {
         Object object=new Object();
         new Thread(new Runnable() {
             @Override
             public void run() {
                 synchronized (object){
                     System.out.println("wait long start");
                     try {
                         object.wait(5000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println("wait long end");
                 }
             }
         }).start();
    }
}
