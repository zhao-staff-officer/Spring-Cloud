package com.cloud.staff.demo.Thread.suspend;

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
