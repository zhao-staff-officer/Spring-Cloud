package com.cloud.staff.apifirst.controller.jdkproxyAndCGlib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements  InvocationHandler{
	
	private Object targetObject;//需要代理的目标对象 
	
	public Object newProxy(Object targetObject) {//将目标对象传入进行代理    
        this.targetObject = targetObject;     
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),    
                targetObject.getClass().getInterfaces(), this);//返回代理对象    
    }    

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
