package com.cloud.staff.demo.Spring.IOC.create;

/**
 * 构造注入
 */
public class ConstructorInjection {

    private ApplicationDemoEntity applicationDemoEntity;

    public ConstructorInjection(ApplicationDemoEntity applicationDemoEntity){
        this.applicationDemoEntity = applicationDemoEntity;
    }
}
