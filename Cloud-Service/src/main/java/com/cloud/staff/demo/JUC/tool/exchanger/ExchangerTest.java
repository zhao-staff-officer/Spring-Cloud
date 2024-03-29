package com.cloud.staff.demo.JUC.tool.exchanger;

import java.util.concurrent.Exchanger;

/**
 * exchanger只支持2个线程，不支持多线程
 *
 */
public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger=new Exchanger<>();
        Thread[] threads = new Thread[2];
        threads[0] = new ExchangerDemo(exchanger, "a", "thread0");
        threads[1] = new ExchangerDemo(exchanger, "b", "thread1");
        threads[0].start();
        threads[1].start();
    }
}
