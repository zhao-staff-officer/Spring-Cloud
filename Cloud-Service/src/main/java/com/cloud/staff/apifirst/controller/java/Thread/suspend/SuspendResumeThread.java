package com.cloud.staff.apifirst.controller.java.Thread.suspend;

import lombok.Data;

@Data
public class SuspendResumeThread extends Thread{
    private long i=0;

    @Override
    public void run (){
        while(true){
            i++;
        }
    }
}
