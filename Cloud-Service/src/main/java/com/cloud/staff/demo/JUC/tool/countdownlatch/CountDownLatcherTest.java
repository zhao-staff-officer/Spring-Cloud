package com.cloud.staff.demo.JUC.tool.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatcherTest {
    public static void main(String[] args) {
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义计数器
        CountDownLatch countDownLatch =new CountDownLatch(10);
        for(int i =0;i<10;i++){
            CountDownLatcherDemo countDownLatcherDemo =new CountDownLatcherDemo(countDownLatch);
            executorService.execute(countDownLatcherDemo);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行结束");
        executorService.shutdown();


    }
}
