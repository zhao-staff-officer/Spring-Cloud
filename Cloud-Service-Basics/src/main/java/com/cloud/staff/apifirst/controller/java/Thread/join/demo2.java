package com.cloud.staff.apifirst.controller.java.Thread.join;

/**
 * @ClassName demo2
 * @Description : join与异常
 * @Return :
 * @Author : 赵参谋
 * @Date : 2020/5/14 14:24
*/
public class demo2 {
    public static void main(String[] args) {

      Thread threadA =  new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<Integer.MAX_VALUE;i++){
                    String str=new String();
                    Math.random();
                }
            }
        },"A");

      Thread threadB=new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   threadA.start();
                   threadA.join();
                   System.out.println("线程B在RUN EDN 处打印了");
               } catch (InterruptedException e) {
                   System.out.println("线程B在CATCH 处打印了");
                   e.printStackTrace();
               }
           }
       },"B");

      Thread threadC=new Thread(new Runnable() {
          @Override
          public void run() {
              threadB.interrupt();
          }
      },"C");

        try {
            threadB.start();
            Thread.sleep(500);
            threadC.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
