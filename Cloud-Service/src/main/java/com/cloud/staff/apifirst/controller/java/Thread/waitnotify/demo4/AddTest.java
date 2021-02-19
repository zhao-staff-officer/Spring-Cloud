package com.cloud.staff.apifirst.controller.java.Thread.waitnotify.demo4;

import java.util.ArrayList;
import java.util.List;

public class AddTest {
    private List list=new ArrayList<>();

    public void add(){
        list.add("测试");
    }

    public int size(){
        return list.size();
    }

}
