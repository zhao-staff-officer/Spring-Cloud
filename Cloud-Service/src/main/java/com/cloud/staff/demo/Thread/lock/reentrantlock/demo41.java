package com.cloud.staff.demo.Thread.lock.reentrantlock;

import java.util.concurrent.locks.Lock;

public class demo41 {

    private  Lock lock ;

    public demo41(Lock lock){
        this.lock=lock;
    }

    public void service(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获取锁");
        }finally {
            lock.unlock();
        }
    }
}
