package com.cloud.staff.apifirst.controller.java.DesignPatterns.ChainOfResponsibility;

/**
 * 责任链
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。
 * 将这些对象连成一条链，并沿着这条链发送该请求，直到有一个对象处理它为止
 * @author zhaoHB 
 *
 */
public class ChainOfResponsibilityMain {
	
	public static void main(String[] args) {
		String msg="我日，尼玛，你爸，测试";
		FilterChain chain=new FilterChain();
		chain.addFilter(new FilterOne()).addFilter(new FilterTwo());
		msg=chain.doFilter(msg);
		System.out.println(msg);
	}

}
