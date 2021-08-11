package com.cloud.staff.demo.Thread.isalive;
//判断线程是否工作
public class Test {
    public static void main(String[] args) throws InterruptedException {
        IsAlive ia=new IsAlive();
        System.out.println("begin="+ia.isAlive());
        ia.start();
        Thread.sleep(1000);
        System.out.println("end="+ia.isAlive());
    }
}
