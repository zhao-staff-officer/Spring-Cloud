package com.cloud.staff.demo.Thread.isalive;

public class IsAlive extends Thread{
    @Override
    public void  run(){
        System.out.println("run"+this.isAlive()+this.getName());
    }
}
