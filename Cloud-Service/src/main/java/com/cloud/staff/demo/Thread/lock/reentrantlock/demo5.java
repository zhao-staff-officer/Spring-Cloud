package com.cloud.staff.demo.Thread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * getHoldCount()
 * 查询当前线程持此锁的个数，也就是调用lock()方法的次数
 */
public class demo5 {
    private ReentrantLock reentrantLock =new ReentrantLock();

    public void serviceMethod1(){
        reentrantLock.lock();
        try{
           System.out.println(Thread.currentThread().getName()+":"+reentrantLock.getHoldCount());
           serviceMethod2();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void serviceMethod2(){
        reentrantLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+":"+reentrantLock.getHoldCount());

        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        demo5 demo5=new demo5();
        demo5.serviceMethod1();
    }
}
