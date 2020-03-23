package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo4;

public class PublicVar {
    public String userName="A";
    public String userPass="AA";
    synchronized public void setUser(String userName,String userPass){
        try {
            this.userName=userName;
            Thread.sleep(5000);
            this.userPass=userPass;
            System.out.println("SET  == userName="+userName+",userPass="+userPass);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    synchronized public void getUser(){
        System.out.println("GET  == userName="+userName+",userPass="+userPass);
    }


}
