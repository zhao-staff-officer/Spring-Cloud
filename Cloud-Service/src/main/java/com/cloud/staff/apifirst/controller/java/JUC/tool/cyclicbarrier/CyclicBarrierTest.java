package com.cloud.staff.apifirst.controller.java.JUC.tool.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatcher与CyclicBarrier区别
 * CoungtDownLatcher是基于AQS实现，CyclicBarrier是基于lock.conndition
 * CoungtDownLatcher计数器减法，不可以重用。CyclicBarrier基于加法通过reset可以重用
 * CoungtDownLatcher父线程调用await()等待子线程CoungtDownLatcher计数器为0，CyclicBarrier是子线程嗲用await等待其余线程执行完毕。
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //创建线程池
        ExecutorService executorService=  Executors.newCachedThreadPool();
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程执行完毕");
            }
        });
        Thread[] threads=new Thread[5];
        for(int i =0;i<threads.length;i++){
            threads[i] = new CyclicBarrierDemo(cyclicBarrier);
            executorService.execute(threads[i]);
        }
    }
}
