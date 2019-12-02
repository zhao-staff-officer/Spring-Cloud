package com.cloud.staff.apifirst.controller.java.Thread.instancevariaable;

public class Test {
    public static void main(String[] args) {
        NotShar ns1=new NotShar("A");
        NotShar ns2=new NotShar("B");
        NotShar ns3=new NotShar("c");
        ns1.start();
        ns2.start();
        ns3.start();
    }
}
