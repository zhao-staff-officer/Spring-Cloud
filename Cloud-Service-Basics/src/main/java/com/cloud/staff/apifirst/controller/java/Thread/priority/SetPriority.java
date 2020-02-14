package com.cloud.staff.apifirst.controller.java.Thread.priority;


//设置优先级
public class SetPriority {
    public static void main(String[] args) {
        System.out.println("main-thread-priority:"+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("main-thread-priority:"+Thread.currentThread().getPriority());
        Thread1 thread1=new Thread1();
        thread1.start();
    }

    static class Thread1 extends  Thread{
        @Override
        public void run (){
            System.out.println("thread1:"+currentThread().getPriority());
            Thread2 thread2=new Thread2();
            thread2.start();
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run(){
            System.out.println("thread2:"+currentThread().getPriority());
        }
    }
}
