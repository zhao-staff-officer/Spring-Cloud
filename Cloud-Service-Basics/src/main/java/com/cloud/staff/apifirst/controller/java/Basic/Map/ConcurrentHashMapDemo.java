package com.cloud.staff.apifirst.controller.java.Basic.Map;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        concurrentHashMap.put(1,1);

        concurrentHashMap.get(1);
    }
}
