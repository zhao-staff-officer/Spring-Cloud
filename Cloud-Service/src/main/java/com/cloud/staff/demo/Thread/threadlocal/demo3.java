package com.cloud.staff.demo.Thread.threadlocal;

/**
 * threadlocal get null 问题
 */
public class demo3 {

    static  class ThreadLocalExtends extends ThreadLocal{

        @Override
        protected Object initialValue() {
            return "初始化值";
        }

    public static ThreadLocalExtends threadLocalExtends=new ThreadLocalExtends();

    public static void main(String[] args) {
        if(threadLocalExtends.get() == null ){
            System.out.println("未初始化值");
            threadLocalExtends.set("设置值");
        }
        System.out.println(threadLocalExtends.get());

    }

    }
}
