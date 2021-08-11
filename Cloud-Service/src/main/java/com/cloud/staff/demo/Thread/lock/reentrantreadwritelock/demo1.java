package com.cloud.staff.demo.Thread.lock.reentrantreadwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock
 * 读读共享
 */
public class demo1 {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void readMethod(){
        reentrantReadWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获取锁"+System.currentTimeMillis());
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}
