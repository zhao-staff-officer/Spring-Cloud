package com.cloud.staff.apifirst.controller.java.Thread.join;

/**
 * @ClassName demo1
 * @Description : join
 *
 * @param null
 * @Return :
 * @Author : 赵参谋
 * @Date : 2020/5/14 14:09
*/
public class demo1 {

    public static void main(String[] args) {
        try {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    int sencodValue=(int)(Math.random()*1000);
                    System.out.println(sencodValue);
                    try {
                        Thread.sleep(sencodValue);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            thread.join();
            System.out.println("我想当子线程执行结束完毕再执行");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}

/**
 * @ClassName demo1
 * @Description : join源码分析
 *
 * @param null
 * @Return : jon 源码分析
 * @Author : 赵参谋
 * @Date : 2020/5/14 14:15
*/

//public final void join() throws InterruptedException {
//    join(0);
//}


//    public final synchronized void join(long millis)
//            throws InterruptedException {
//        获取当前时间
//        long base = System.currentTimeMillis();
//        long now = 0;
//
//        if (millis < 0) {
//            throw new IllegalArgumentException("timeout value is negative");
//        }
//        如果millis为0,判断线程是否存活，存活则无线循环wait
//        if (millis == 0) {
//            while (isAlive()) {
//                wait(0);
//            }
//        } else {
//        若果millis不为0,计算等待时间，日过时间小于0则直接退出，如果大于0则等待时间，计算下次等待时间
//            while (isAlive()) {
//                long delay = millis - now;
//                if (delay <= 0) {
//                    break;
//                }
//                wait(delay);
//                now = System.currentTimeMillis() - base;
//            }
//        }
//    }
