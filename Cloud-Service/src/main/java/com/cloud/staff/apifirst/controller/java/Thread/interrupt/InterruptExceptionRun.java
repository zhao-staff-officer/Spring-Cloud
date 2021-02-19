package com.cloud.staff.apifirst.controller.java.Thread.interrupt;

public class InterruptExceptionRun {
    public static void main(String[] args) {
        try {
            InterruptException interruptException = new InterruptException();
            interruptException.start();
            Thread.sleep(2000);
            interruptException.interrupt();
        }catch (Exception e){
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}
