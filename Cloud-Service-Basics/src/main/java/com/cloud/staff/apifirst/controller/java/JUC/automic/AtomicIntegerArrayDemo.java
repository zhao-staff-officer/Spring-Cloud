package com.cloud.staff.apifirst.controller.java.JUC.automic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子类数组
 * AtomicIntegerArray,AtomicLongArray
 */
public class AtomicIntegerArrayDemo {
    static int[] value= new int[]{1,2};
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);
    public static void main(String[] args) {
         System.out.println(atomicIntegerArray.getAndSet(0,3));
         System.out.println(atomicIntegerArray.get(0));
         System.out.println(value[0]);

    }
}
