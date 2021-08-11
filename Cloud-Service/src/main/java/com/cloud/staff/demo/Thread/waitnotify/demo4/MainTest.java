package com.cloud.staff.demo.Thread.waitnotify.demo4;

import lombok.SneakyThrows;

public class MainTest {

    public static void main(String[] args) {
        Object object=new Object();
        AddTest addTest=new AddTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                   for(int i=0;i<10;i++){
                       System.out.println("A 线程 添加"+(i+1)+"个集合");
                       addTest.add();
                       synchronized (object){
                           if(addTest.size()==5){
                               System.out.println("A 线程 唤醒object");
                               object.notify();
                           }
                       }

               }
            }
        },"A").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                synchronized (object){
                    System.out.println("B 线程wait 状态");
                    object.wait();
                    System.out.println("B 线程running 状态");
                }
            }
        },"B").start();


    }
}
