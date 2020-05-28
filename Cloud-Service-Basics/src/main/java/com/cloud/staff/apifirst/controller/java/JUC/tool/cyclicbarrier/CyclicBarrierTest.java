package com.cloud.staff.apifirst.controller.java.JUC.tool.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
