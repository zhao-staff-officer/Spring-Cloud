package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo4;

public class Test {
    public static void main(String[] args) {
        try {
            PublicVar publicVar=new PublicVar();
            ThreadA threadA=new ThreadA(publicVar);
            threadA.start();
            Thread.sleep(500);
            publicVar.getUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    /**
     * 当synchornized 修饰方法，线程获取实例对象锁
     * 1：同步执行synchornized 修饰方法，可以调用非synchornized方法
     * 2：调用对象synchornized非相同方法，必须等到线程释放锁。
     */
}
