package com.cloud.staff.demo.Thread.volatiledemo.demo1;

public class PrintString {

    private boolean isContinuePrint=true;

    public boolean isContinuePrint(){
        return isContinuePrint;
    }

    public void setContinuePrint(boolean isContinuePrint){
        this.isContinuePrint=isContinuePrint;
    }

    public void printStringMethod(){
        try {
            while (isContinuePrint){
                System.out.println("printStringMethod Thread Name ="+Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
