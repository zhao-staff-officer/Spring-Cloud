package com.cloud.staff.demo.Thread.yield;

/**
 *yield放弃当前cpu资源，将它让给其他任务占用cpu处理
 */
public class YieldRun {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Long beginTime=System.currentTimeMillis();
                int count=0;
                for(int i=0;i<5000000;i++){
                    Thread.yield();
                    count++;
                }
                long endTime=System.currentTimeMillis();
                System.out.println("用时："+(endTime-beginTime)+"毫秒");
            }
        }).start();

        new Thread(() ->{
                Long beginTime=System.currentTimeMillis();
                int count=0;
                for(int i=0;i<5000000;i++){
                    Thread.yield();
                    count++;
                }
                long endTime=System.currentTimeMillis();
                System.out.println("用时："+(endTime-beginTime)+"毫秒");
        }).start();
    }
}
