package com.cloud.staff.demo.Thread.inheritablethreadlocal;

/**
 * inheritablethreadlocal
 * 继承并修改
 */
public class demo2 {

    static class InheritableThread extends InheritableThreadLocal{

        @Override
        protected Object initialValue() {
            return System.currentTimeMillis();
        }
        @Override
        protected Object childValue(Object parentValue) {
            return parentValue+"添加值";
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
