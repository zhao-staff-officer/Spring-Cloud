package com.cloud.staff.demo.Spring.IOC.create;

/**
 * Setter注入
 */
public class SetterInjection {
    private ApplicationDemoEntity applicationDemoEntity;

    public void setApplicationDemoEntity(ApplicationDemoEntity applicationDemoEntity){
        this.applicationDemoEntity=applicationDemoEntity;
    }
}
