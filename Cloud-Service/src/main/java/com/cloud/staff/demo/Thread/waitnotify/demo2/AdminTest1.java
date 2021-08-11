package com.cloud.staff.demo.Thread.waitnotify.demo2;

/**
 * wait/noify必须获取线程同步锁
 */
public class AdminTest1 {
    public static void main(String[] args) {
        try {
            String newString =new String("");
            newString.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
