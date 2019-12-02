package com.cloud.staff.apifirst.controller.java.Thread.instancevariaable;

public class Test2 {
    public static void main(String[] args) {
        SharVar sv=new SharVar();
        Thread th1=new Thread(sv,"A");
        Thread th2=new Thread(sv,"B");
        Thread th3=new Thread(sv,"C");
        Thread th4=new Thread(sv,"D");
        Thread th5=new Thread(sv,"F");
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
    }
}
