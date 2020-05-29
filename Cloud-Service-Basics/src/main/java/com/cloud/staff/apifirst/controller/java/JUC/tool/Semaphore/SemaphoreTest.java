package com.cloud.staff.apifirst.controller.java.JUC.tool.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore控制线程执行数量
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(3);
        Thread[] threads=new Thread[10];
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<threads.length;i++){
            Runnable runnable=new SemaphoreDemo(semaphore);
            executorService.execute(runnable);
        }
    }
}
