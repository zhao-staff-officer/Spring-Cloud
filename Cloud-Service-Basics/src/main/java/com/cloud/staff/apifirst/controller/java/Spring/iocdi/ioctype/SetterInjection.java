package com.cloud.staff.apifirst.controller.java.Spring.iocdi.ioctype;

/**
 * Setter注入
 */
public class SetterInjection {
    private ApplicationDemoEntity applicationDemoEntity;

    public void setApplicationDemoEntity(ApplicationDemoEntity applicationDemoEntity){
        this.applicationDemoEntity=applicationDemoEntity;
    }
}
