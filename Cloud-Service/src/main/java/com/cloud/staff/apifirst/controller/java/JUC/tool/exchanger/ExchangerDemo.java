package com.cloud.staff.apifirst.controller.java.JUC.tool.exchanger;

import java.util.concurrent.Exchanger;

/**
 * exchanger数据交换
 */
public class ExchangerDemo  extends Thread{

    private Exchanger<String> exchanger;

    private String exchangeValue;

    private String threadName;

    public ExchangerDemo(Exchanger<String> exchanger,String exhancgeValue,String threadName){
        this.exchanger=exchanger;
        this.exchangeValue=exhancgeValue;
        this.threadName=threadName;
    }

    @Override
    public void run(){
        try {
            System.out.println(Thread.currentThread().getName()+":"+exchanger.exchange(exchangeValue));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
