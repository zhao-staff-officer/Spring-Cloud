package com.cloud.staff.demo.Thread.Daemon;

/**
 * 守护线程
 */
public class Daemon {
    public static void main(String[] args) {
        try {
            Daemon2 daemon2 = new Daemon2();
            daemon2.setDaemon(true);
            daemon2.start();
            Thread.sleep(50000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    static class Daemon2 extends  Thread{
        private  int i=0;
        @Override
        public void run(){
           try{
             while (true){
                 i++;
                 System.out.println("i="+i);
                 Thread.sleep(1000);
             }
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }

    }
}
