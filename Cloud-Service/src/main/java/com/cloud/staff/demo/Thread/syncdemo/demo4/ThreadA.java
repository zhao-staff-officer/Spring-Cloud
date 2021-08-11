package com.cloud.staff.demo.Thread.syncdemo.demo4;

public class ThreadA extends Thread{

    public PublicVar publicVar;

    public ThreadA(PublicVar publicVar){
        this.publicVar=publicVar;
    }

    @Override
    public void run(){
        publicVar.setUser("B","BB");
    }
}
