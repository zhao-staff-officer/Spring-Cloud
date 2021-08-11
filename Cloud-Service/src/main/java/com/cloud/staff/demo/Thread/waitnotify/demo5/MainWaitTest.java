package com.cloud.staff.demo.Thread.waitnotify.demo5;

import lombok.SneakyThrows;

/**
 * @ClassName MainWaitTest
 * @Description : wait后释放锁
 * @Return :
 * @Author : 赵参谋
 * @Date : 2020/4/28 0:26
*/
public class MainWaitTest {

    public static void main(String[] args) {
        WaitTest waitTest=new WaitTest();
        Object object=new Object();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                waitTest.waitTest(object);
            }
        },"A").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                waitTest.waitTest(object);
            }
        },"B").start();

    }
}
