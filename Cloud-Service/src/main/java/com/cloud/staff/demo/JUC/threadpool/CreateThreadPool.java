package com.cloud.staff.demo.JUC.threadpool;

import java.util.concurrent.*;

/**
 * 创建线程池
 */
public class CreateThreadPool {
    public static void main(String[] args) {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        System.out.println(corePoolSize);
        ThreadPoolExecutor threadPoolExecutor =new ThreadPoolExecutor(5,6,3,TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));

        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
              System.out.println(Thread.currentThread().getName());
            }
        });
        threadPoolExecutor.execute(thread);
    }
}

//    int corePoolSize,    核心线程池大小
/**
 *    IO密集型2N,计算密集型N+1，N=Runtime.getRuntime.availbleProcessors()
 */
//    int maximumPoolSize, 最大线程池大小
//    long keepAliveTime,  线程最大空闲时间
//    TimeUnit unit,  单位时间
//    BlockingQueue<Runnable> workQueue, 线程等待队列
//    ThreadFactory threadFactory,  线程创建工厂
//    RejectedExecutionHandler handler 拒绝策略

//线程池工作过程
//1：创建核心线程池，2核心线程池满了放入blockingqueue等待队列，3等待队列满了，若果小于最大线程池创建线程执行，反之执行拒绝策略
