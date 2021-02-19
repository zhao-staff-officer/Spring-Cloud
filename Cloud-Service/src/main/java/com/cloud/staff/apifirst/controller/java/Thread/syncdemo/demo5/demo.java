package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo5;

public class demo {

    synchronized public void service1(){
        System.out.println("service1");
        service2();
    }

    synchronized public void service2(){
        System.out.println("service2");
        service3();
    }

    synchronized public void service3(){
        System.out.println("service3");
    }
}
