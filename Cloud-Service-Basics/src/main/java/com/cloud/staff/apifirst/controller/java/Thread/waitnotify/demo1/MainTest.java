package com.cloud.staff.apifirst.controller.java.Thread.waitnotify.demo1;

import lombok.SneakyThrows;

public class MainTest {
    public static void main(String[] args) {
        ListAdd listAdd=new ListAdd();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
               for(int i=0;i<10;i++){
                   listAdd.add();
                   System.out.println("添加了"+(i+1)+"个元素");
                   Thread.sleep(1000);
               }
            }
        },"A").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while(true){
                    if(listAdd.size()==5){
                        System.out.println("size=5线程退出");
                        throw new InterruptedException();
                    }
                }
            }
        },"B").start();

    }
}
