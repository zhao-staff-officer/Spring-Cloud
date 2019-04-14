package com.cloud.staff.apifirst.controller.java.DesignPatterns.FactoryMethod;

/**
 * 工厂方法
 * 是由子类来创建对象
 * 
 */
public class TestMain {
	public static void main(String[] args) {
		OlgorFactory olgor=new OlgorAdd();
		olgor.olgor();
	}

}
