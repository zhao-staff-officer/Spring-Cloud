package com.cloud.staff.apifirst.controller.java.Spring.iocdi.ioctype;

import lombok.Data;

/**
 * 测试实体
 */
public class ApplicationDemoEntityClass {

    private String name;

    private int age;

   /*有参构造方法注入*/
    public ApplicationDemoEntityClass(String name,int age){
        System.out.println("Spring有参构造方法注入");
       this.name=name;
       this.age=age;
    }
    /*无参构造方法*/
    public ApplicationDemoEntityClass(){

    }

   /*初始化方法*/
    public void initMenthod(){
        System.out.println("Spring初始化执行方法");
    }

    /*静态方法实例化对象*/
    public static ApplicationDemoEntityClass createStaticBean(){
        System.out.println("Spring实例化对象");
        return new ApplicationDemoEntityClass();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
