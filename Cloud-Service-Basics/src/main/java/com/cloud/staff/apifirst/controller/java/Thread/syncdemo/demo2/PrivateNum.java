package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo2;

/**
 * 全局变量线程不安全
 */
public class PrivateNum {
    private int num=0;
    public void addNum(String name ){
        try{
            if(name.equals("a")){
                num=100;
                System.out.println("a add over");
                Thread.sleep(2000);
            }else if(name.equals("b")){
                num=200;
                System.out.println("b add over");
            }
            System.out.println(name +"="+ num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    static class ThreadA {
        private PrivateNum privateNum;
        public ThreadA(PrivateNum privateNum){
            this.privateNum=privateNum;
        }

        public void ThreadAadd(){
            new Thread(() ->{
                privateNum.addNum("a");
            }).start();
        }
    }

    static class ThreadB {
        private PrivateNum privateNum;
        public ThreadB(PrivateNum privateNum){
            this.privateNum=privateNum;
        }

        public void ThreadBadd(){
            new Thread(() ->{
                privateNum.addNum("b");
            }).start();
        }
    }

    public static void main(String[] args) {
        PrivateNum privateNum=new PrivateNum();
        ThreadA threadA=new ThreadA(privateNum);
        threadA.ThreadAadd();
        ThreadB threadB=new ThreadB(privateNum);
        threadB.ThreadBadd();
    }
}
