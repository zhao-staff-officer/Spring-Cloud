package com.cloud.staff.apifirst.controller.java.Spring.IOC.create;

/**
 * 构造注入
 */
public class ConstructorInjection {

    private ApplicationDemoEntity applicationDemoEntity;

    public ConstructorInjection(ApplicationDemoEntity applicationDemoEntity){
        this.applicationDemoEntity = applicationDemoEntity;
    }
}
