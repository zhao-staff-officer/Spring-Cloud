package com.cloud.staff.demo.Thread.suspend;

/**
 * 线程暂停与恢复
 * suspend 暂停
 * resume  恢复
 */
public class SuspendResumeRun {
    public static void main(String[] args) {
        try{
            SuspendResumeThread thread=new SuspendResumeThread();
            thread.start();
            Thread.sleep(5000);
            //A段
            thread.suspend();
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(5000);
            System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());

            //B段
            thread.resume();
            Thread.sleep(5000);

            //C段
            thread.suspend();
            System.out.println("C="+System.currentTimeMillis()+" i="+thread.getI());
            Thread.sleep(5000);
            System.out.println("C="+System.currentTimeMillis()+" i="+thread.getI());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
