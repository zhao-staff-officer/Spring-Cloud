package com.cloud.staff.apifirst.controller.java.Thread.interrupt;

/**
 * interrupt停止现场
 */
public class InterruptException extends Thread{
    @Override
    public void run(){
        super.run();
        try{
            System.out.println("开始");
           for(int i=0;i<500000;i++){
               if(Thread.interrupted()){
                  System.out.println("线程停止");
                  throw  new InterruptedException();
               }
               System.out.println("i="+i);
           }
        }catch (InterruptedException e){
             System.out.println("catch InterruptException");
             e.printStackTrace();
        }
    }
}
