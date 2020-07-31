package com.cloud.staff.apifirst.controller.java.Spring.IOC;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;

/**
 * 依赖拖拽
 */
public class DependencyPull {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new org.springframework.context.support.ClassPathXmlApplicationContext("application.xml");
        ApplicationDemoEntity applicationDemoEntity = applicationContext.getBean("ApplicationDemoEnity3", ApplicationDemoEntity.class);
        System.out.println(JSONObject.toJSONString(applicationDemoEntity));
    }
}
