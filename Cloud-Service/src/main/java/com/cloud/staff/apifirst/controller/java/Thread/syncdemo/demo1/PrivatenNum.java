package com.cloud.staff.apifirst.controller.java.Thread.syncdemo.demo1;

/**
 * 方法内部变量线程安全
 */
public class PrivatenNum {
    public void addNum(String name ){
        try{
            int num=0;
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
        private PrivatenNum privatenNum;
        public ThreadA(PrivatenNum privatenNum){
           this.privatenNum=privatenNum;
        }

        public void ThreadAadd(){
            new Thread(() ->{
                privatenNum.addNum("a");
            }).start();
        }
    }

    static class ThreadB {
        private PrivatenNum privatenNum;
        public ThreadB(PrivatenNum privatenNum){
            this.privatenNum=privatenNum;
        }

        public void ThreadBadd(){
            new Thread(() ->{
                privatenNum.addNum("b");
            }).start();
        }
    }

    public static void main(String[] args) {
        PrivatenNum privatenNum=new PrivatenNum();
        ThreadA threadA=new ThreadA(privatenNum);
        threadA.ThreadAadd();
        ThreadB threadB=new ThreadB(privatenNum);
        threadB.ThreadBadd();
    }
}
