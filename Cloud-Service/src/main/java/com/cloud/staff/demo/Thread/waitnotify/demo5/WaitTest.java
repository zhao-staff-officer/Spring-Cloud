package com.cloud.staff.demo.Thread.waitnotify.demo5;

public class WaitTest {

    public void waitTest(Object obj) throws InterruptedException {
        synchronized (obj){
            System.out.println("wait 开始");
            obj.wait();
            System.out.println("wait 结束");
        }
    }
}
