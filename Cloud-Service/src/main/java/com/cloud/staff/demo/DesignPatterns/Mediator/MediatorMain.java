package com.cloud.staff.demo.DesignPatterns.Mediator;

/**
 * Mediator
 * 中介者
 * 集中相关对象之间复杂的沟通和控制方式。
 * Mediator：中介者，定义一个接口用于与各同事（Colleague）对象通信。
 * Colleague：同事，相关对象
 * @author zhaoHB
 *
 */
public class MediatorMain {

	public static void main(String[] args) {
		ColleageType1 type1=new ColleageType1();
		ColleageType2 type2=new ColleageType2();
		Mediator meditor=new MediatorEvent(type1, type2);
		type1.doEvent(meditor);
//		type1
	}

}
