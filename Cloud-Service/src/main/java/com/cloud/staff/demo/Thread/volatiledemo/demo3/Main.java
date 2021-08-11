package com.cloud.staff.demo.Thread.volatiledemo.demo3;

/**
 * volatile非原子性
 */
public class Main {
    public static void main(String[] args) {
        addCount[] addCounts=new addCount[100];
        for(int i=0;i<100;i++){
            addCounts[i]=new addCount();
        }
        for(int i=0;i<100;i++){
            addCounts[i].start();
        }
    }
}
