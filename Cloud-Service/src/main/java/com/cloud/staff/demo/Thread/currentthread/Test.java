package com.cloud.staff.demo.Thread.currentthread;

public class Test {
//    public static void main(String[] args) {
//        CurrentThread ct=new CurrentThread();
//        Thread th=new Thread(ct);
//        th.start();
//        //构造方法main
//        //run方法Thread-0
//    }

        public static void main(String[] args) {
        CurrentThread ct=new CurrentThread();
        Thread th=new Thread(ct);
        th.run();
        //构造方法main
        //run方法main
    }
}
