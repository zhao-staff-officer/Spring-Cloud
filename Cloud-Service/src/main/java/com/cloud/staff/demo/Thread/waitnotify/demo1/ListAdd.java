package com.cloud.staff.demo.Thread.waitnotify.demo1;

import java.util.ArrayList;
import java.util.List;

public class ListAdd {

    private List list=new ArrayList<>();

    public void add (){
        list.add("添加数据");
    }

    public int size(){
        return  list.size();
    }
}
