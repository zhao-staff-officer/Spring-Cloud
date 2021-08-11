package com.cloud.staff.demo.JUC.threadpool;

import java.util.concurrent.*;

/**
 * 线程池等待队列
 * 有界队列：ArrayBlockQueue,LinkedBlockQueue,SynchronousQueue
 * 无界队列:DelayedQueue,PriorityBlockingQueue
 */
public class BlockingQueueDemo {
    int coreSize=Runtime.getRuntime().availableProcessors();
    ThreadPoolExecutor executor=new ThreadPoolExecutor(coreSize,coreSize,10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));
    //LinkedBlockingQueue
    ExecutorService executorService1=Executors.newSingleThreadExecutor();
    //LinkedBlockingQueue
    ExecutorService executorService2=Executors.newFixedThreadPool(coreSize);
    //SynchronousQueue
    ExecutorService executorService3=Executors.newCachedThreadPool();
    //DelayedWorkQueue
    ExecutorService executorService4=Executors.newScheduledThreadPool(coreSize);
}
