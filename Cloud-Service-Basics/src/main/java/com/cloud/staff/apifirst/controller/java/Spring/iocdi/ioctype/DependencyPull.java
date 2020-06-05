package com.cloud.staff.apifirst.controller.java.Spring.iocdi.ioctype;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.ApplicationContext;

/**
 * 依赖拖拽
 */
public class DependencyPull {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new org.springframework.context.support.ClassPathXmlApplicationContext("application.xml");
        ApplicationDemoEntityClass applicationDemoEntityClass = applicationContext.getBean("ApplicationDemoEnity3",ApplicationDemoEntityClass.class);
        System.out.println(JSONObject.toJSONString(applicationDemoEntityClass));
    }
}