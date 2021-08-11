package com.cloud.staff.demo.JUC.tool.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量控制访问
 */
public class SemaphoreDemo implements Runnable{

    private Semaphore semaphore;

    public SemaphoreDemo(Semaphore semaphore){
        this.semaphore=semaphore;
    }

    @Override
    public void run(){
        try {
            //获取执行证书
            semaphore.acquire();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+"执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放证书
            semaphore.release();
        }
    }

}
