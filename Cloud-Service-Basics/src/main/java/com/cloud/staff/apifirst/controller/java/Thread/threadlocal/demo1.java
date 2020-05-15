package com.cloud.staff.apifirst.controller.java.Thread.threadlocal;

/**
 * threadlocal
 *
 */
public class demo1 {

    public static ThreadLocal threadLocal=new ThreadLocal();

    public static void main(String[] args) {
        if(threadLocal.get()==null){
           System.out.println("没有值");
           threadLocal.set("设置值");
        }
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
    }
}
