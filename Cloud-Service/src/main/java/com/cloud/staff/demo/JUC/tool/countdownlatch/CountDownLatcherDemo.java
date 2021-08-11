package com.cloud.staff.demo.JUC.tool.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatcherDemo implements Runnable {

    private CountDownLatch countDownLatch;

    public CountDownLatcherDemo(CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
        //数值减1
        countDownLatch.countDown();
    }
}
