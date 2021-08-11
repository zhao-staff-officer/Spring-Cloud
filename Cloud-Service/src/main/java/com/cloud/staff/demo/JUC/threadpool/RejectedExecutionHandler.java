package com.cloud.staff.demo.JUC.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池拒绝策略
 * AbortPolicy 拒绝线程加入抛异常
 * DiscardPolicy 拒绝加入，不抛异常
 * DiscardOldestPolict 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * CallerRunPolicy 由调用线程处理该任务
 */
@Slf4j
public class RejectedExecutionHandler {

    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    private static int maxCorePoolSize = corePoolSize;
    private static final java.util.concurrent.RejectedExecutionHandler abortPolicyHandler = new ThreadPoolExecutor.AbortPolicy();
    private static final java.util.concurrent.RejectedExecutionHandler discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
    private static final java.util.concurrent.RejectedExecutionHandler discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
    private static final java.util.concurrent.RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

    public static void main(String[] args) {

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxCorePoolSize, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), abortPolicyHandler);
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxCorePoolSize, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), discardPolicy);
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxCorePoolSize, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), discardOldestPolicy);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxCorePoolSize, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), callerRunsPolicy);

        for(int i =0 ;i<100;i++){
            int finalI = i;
            executor.execute(new Thread(() ->{
               log.info(Thread.currentThread().getName() +"is Running:"+ finalI);
            }));
        }
        executor.shutdown();
    }
}
