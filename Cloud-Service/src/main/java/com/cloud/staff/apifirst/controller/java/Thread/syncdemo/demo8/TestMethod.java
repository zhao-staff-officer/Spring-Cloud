package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo8;

public class TestMethod {

    private String getData1;
    private String getData2;
    public void doLongTimeTask(){
        System.out.println("TimeTaskBegin");
        try {
            Thread.sleep(3000);
            getData1="长时间处理任务1返回数据"+Thread.currentThread().getName();
            getData2="长时间处理任务1返回数据"+Thread.currentThread().getName();
            synchronized (this){
                System.out.println(getData1);
                System.out.println(getData2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TimeTaskEnd");

    }
}
