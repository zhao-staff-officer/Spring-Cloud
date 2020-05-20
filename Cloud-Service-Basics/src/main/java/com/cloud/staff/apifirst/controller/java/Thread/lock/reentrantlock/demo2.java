package com.cloud.staff.apifirst.controller.java.Thread.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock实现wait/notify
 * Condition实现线程waitting
 */
public class demo2 {

    static class AwaitTest{
        private Lock lock =new ReentrantLock();
        private Condition condition=lock.newCondition();

        public void awaitMethod(){
            lock.lock();
            try{
                System.out.println("A");
                condition.await();
                System.out.println("B");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               AwaitTest awaitTest=new AwaitTest();
               awaitTest.awaitMethod();
            }
        }).start();
    }
}
