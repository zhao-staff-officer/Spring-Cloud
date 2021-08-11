package com.cloud.staff.demo.DesignPatterns.TemplateMethod;

/**
 * 设计模式-模板方法
 * @author zhaoHB
 *定义算法框架，并将一些步骤的实现延迟到子类。
 *通过模板方法，子类可以重新定义算法的某些步骤，而不用改变算法的结构。
 */
public class TemplateMethodMain {
    public static void main(String[] args) {
    	CaffeineBeverage caffeineBeverage = new Coffee();
        caffeineBeverage.CaffeineBeverage();
        System.out.println("-----------");
        caffeineBeverage = new Tea();
        caffeineBeverage.CaffeineBeverage();
	}
//    boilWater
//    Coffee.brew
//    pourInCup
//    Coffee.addCondiments
//    -----------
//    boilWater
//    Tea.brew
//    pourInCup
//    Tea.addCondiments
}
