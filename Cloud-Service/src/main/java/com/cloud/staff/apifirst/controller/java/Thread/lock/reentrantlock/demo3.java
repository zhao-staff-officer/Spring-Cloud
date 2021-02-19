package com.cloud.staff.apifirst.controller.java.Thread.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * 等待通知
 * await 等待
 * signal 通知
 */
public class demo3 {

    static  class ConditionDemo{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void await(){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+" await："+System.currentTimeMillis());
                condition.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void signal(){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+" signal:"+System.currentTimeMillis());
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ConditionDemo conditionDemo=new ConditionDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                conditionDemo.await();
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conditionDemo.signal();
    }
}
