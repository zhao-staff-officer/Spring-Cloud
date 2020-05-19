package com.cloud.staff.apifirst.controller.java.Thread.inheritablethreadlocal;

import javax.xml.crypto.Data;

/**
 * inheritablethreadlocal
 * 继承父类值
 */
public class demo1 {

    static class InheritableThread extends InheritableThreadLocal{

        @Override
        protected Object initialValue() {
            return System.currentTimeMillis();
        }
    }
    static class ThreadA extends Thread{
        @Override
        public void run(){
            for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"获取值："+inheritableThread.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static InheritableThread inheritableThread=new InheritableThread();

    public static void main(String[] args) {
       try {
           for(int i=0;i<10;i++){
               System.out.println(Thread.currentThread().getName()+"获取值："+inheritableThread.get());
               Thread.sleep(100);
           }
           Thread.sleep(5000);
           ThreadA threadA =new ThreadA();
           threadA.start();
       }catch (InterruptedException e){
           e.printStackTrace();
       }
    }


}
