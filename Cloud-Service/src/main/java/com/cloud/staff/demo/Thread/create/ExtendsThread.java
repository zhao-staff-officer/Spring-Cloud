package com.cloud.staff.demo.Thread.create;
//继承Thrad创建线程
public class ExtendsThread extends Thread {
       @Override
       public void run(){
              super.run();
              System.out.println("MYTHREAD");
       }

}
