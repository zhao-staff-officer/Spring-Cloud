package com.cloud.staff.apifirst.controller.java.Thread.isalive;

public class IsAlive extends Thread{
    @Override
    public void  run(){
        System.out.println("run"+this.isAlive()+this.getName());
    }
}
