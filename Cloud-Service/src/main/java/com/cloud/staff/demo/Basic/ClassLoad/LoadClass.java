package com.cloud.staff.demo.Basic.ClassLoad;

public class LoadClass {

	static {
		System.out.println("初始化");
	}

	public static void main(String[] args) throws ClassNotFoundException {
//		System.out.println("----------开始----------");
//		ClassLoader cload=ClassLoader.getSystemClassLoader();
//		cload.loadClass("com.cloud.staff.demo.Basic.ClassLoad.ClassForName");
//		System.out.println("-------------------分隔符--------------------");
//		Class.forName("com.cloud.staff.demo.Basic.ClassLoad.ClassForName");
//		System.out.println("----------结束----------");
//		思考：这里显示loadclass未进行初始胡
//		初始化
//		----------开始----------
//		-------------------分隔符--------------------
//		静态代码块
//		----------结束----------

		System.out.println("----------开始----------");
		Class.forName("com.cloud.staff.demo.Basic.ClassLoad.ClassForName");
		System.out.println("-------------------分隔符--------------------");
		ClassLoader cload2=ClassLoader.getSystemClassLoader();
		cload2.loadClass("com.cloud.staff.demo.Basic.ClassLoad.ClassForName");
		System.out.println("----------结束----------");

//		初始化
//		----------开始----------
//		静态代码块
//		-------------------分隔符--------------------
//		----------结束----------

	}

}
