package com.cloud.staff.demo.Basic.copy;

/**
 * 浅拷贝
 * @author zhaoHB
 *结果显示
 *基础类型是指传递
 *修改一个对象不影响另一个对象
 */
public class ShallowCopy {
	public static void main(String[] args) {
		int a=1;
		System.out.println(a);
		int b=a;
		System.out.println(a);
		System.out.println(b);
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));
		b=2;
		System.out.println(a);
		System.out.println(b);
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));
//		1
//		1
//		1
//		2018699554
//		2018699554
//		1
//		2
//		2018699554
//		1311053135
	}


}
