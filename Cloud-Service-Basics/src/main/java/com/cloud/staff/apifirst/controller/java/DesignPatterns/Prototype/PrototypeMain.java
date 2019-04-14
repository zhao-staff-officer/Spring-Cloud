package com.cloud.staff.apifirst.controller.java.DesignPatterns.Prototype;

/**
 * 使用原型实例指定要创建对象的类型，通过复制这个原型来创建新对象。
 * @author zhaoHB
 *
 */
public class PrototypeMain {
	public static void main(String[] args) {
		Prototype proto=new CreatePrototype("abc");
		Prototype proto2=proto.myclone();
		System.out.println(proto);
		System.out.println(proto2);
		
//		com.cloud.staff.apifirst.controller.java.DesignPatterns.Prototype.CreatePrototype@7852e922
//		com.cloud.staff.apifirst.controller.java.DesignPatterns.Prototype.CreatePrototype@4e25154f
	}

}
