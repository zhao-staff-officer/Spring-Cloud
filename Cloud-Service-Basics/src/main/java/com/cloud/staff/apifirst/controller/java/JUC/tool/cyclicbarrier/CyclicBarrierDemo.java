package com.cloud.staff.apifirst.controller.java.JUC.tool.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CycliBarrier示例
 */
public class CyclicBarrierDemo extends  Thread{

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierDemo(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {
        try {
        System.out.println(Thread.currentThread().getName() + "CycliBarrier正在执行");
            System.out.println(Thread.currentThread().getName() + "CycliBarrier执行结束");
            Thread.sleep((long) (Math.random() * 1000));
            cyclicBarrier.await();
        } catch (InterruptedException |BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
