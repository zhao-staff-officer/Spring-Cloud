package com.cloud.staff.demo.Thread.syncdemo.demo3;


public class MyObject {

    synchronized  public void methodA(){
        try {
            System.out.println("begin methoda threadname="+ Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ThreadA extends Thread{
        private MyObject myObject;

        public  ThreadA(MyObject myObject){
           this.myObject=myObject;
        }

        @Override
        public void run(){
            myObject.methodA();
        }
    }

    static class  ThreadB extends Thread{
        private MyObject myObject;
        public  ThreadB(MyObject myObject){
            this.myObject=myObject;
        }

        @Override
        public void run(){
            myObject.methodA();
        }
    }

    public static void main(String[] args) {
        MyObject myObjectA=new MyObject();
        MyObject myObjectB=new MyObject();
        ThreadA threadA=new ThreadA(myObjectA);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB=new ThreadB(myObjectA);
        threadB.setName("B");
        threadB.start();
    }
    /**
     * synchornized 对象锁
     */
}
