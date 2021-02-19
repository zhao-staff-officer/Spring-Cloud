package com.cloud.staff.apifirst.controller.java.Spring.IOC.create;

/**
 * 测试实体
 */
public class ApplicationDemoEntity {

    private String name;

    private int age;

   /*有参构造方法注入*/
    public ApplicationDemoEntity(String name, int age){
        System.out.println("Spring有参构造方法注入");
       this.name=name;
       this.age=age;
    }
    /*无参构造方法*/
    public ApplicationDemoEntity(){

    }

   /*初始化方法*/
    public void initMenthod(){
        System.out.println("Spring初始化执行方法");
    }

    /*静态方法实例化对象*/
    public static ApplicationDemoEntity createStaticBean(){
        System.out.println("Spring实例化对象");
        return new ApplicationDemoEntity();
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
