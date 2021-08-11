package com.cloud.staff.demo.Thread.syncdemo.demo5;

/**
 * 重入锁
 * 自己可以再次获取自己的内部锁
 */
public class Test {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo demo=new demo();
                demo.service1();
            }
        }).start();
    }
}
