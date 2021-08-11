package com.cloud.staff.demo.JUC.automic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUC原子类操作
 * 基本类型
 * atomicInteger,atomicLong,atomicBoolean
 */
public class AtomicIntegerDemo {

    private static AtomicInteger automicInteger =new AtomicInteger();

    public static void main(String[] args) {
        //自增1，返回自增前值
         System.out.println(automicInteger.getAndIncrement());
        //增加值，返回增加后值
        System.out.println(automicInteger.addAndGet(2));
        //如果输入值与期望值一致，返回true,反之则false
        System.out.println(automicInteger.compareAndSet(1,2));
        //以原子方式设置新值，返回旧值
        System.out.println(automicInteger.getAndSet(2));

        System.out.println(automicInteger.get());
    }
}
