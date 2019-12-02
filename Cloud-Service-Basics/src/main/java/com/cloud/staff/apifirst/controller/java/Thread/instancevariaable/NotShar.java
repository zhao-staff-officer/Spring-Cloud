package com.cloud.staff.apifirst.controller.java.Thread.instancevariaable;
//非共享变量
public class NotShar extends Thread {
    private int count = 5;
    public NotShar(String name){
        super();
        this.setName(name);
    }
    @Override
    public void run(){
       super.run();
       while(count >0){
           count--;
           System.out.println(Thread.currentThread().getName()+"当前计算结果："+count);
       }
    }
}
