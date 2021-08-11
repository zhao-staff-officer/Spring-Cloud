package com.cloud.staff.demo.Basic.ClassLoad;

/**
 * class加载器
 * @author zhaoHB
 *启动类加载器 bootstrap classload
 *扩展类加载器 extendtion classload
 *应用程序加载器 application classload
 *自定义加载器 User classload
 *
 *委派与双亲委派
 * 类加载器会调用父类加载器，如果父类加载器为空，则调用自身加载器
 * 双亲委派例如：使用扩展程序加载器 →扩展类加载器→启动类加载器 调用2次
 * JNDI,JDBC,JCE、JAXB,JBI等
 */
public class ClassLoadMain {
//	验证加载器
	public static void main(String[] args) {
		ClassLoader c=ClassLoadMain.class.getClassLoader();
		System.out.println(c);
		ClassLoader c1=c.getParent();
		System.out.println(c1);
		ClassLoader c2=c1.getParent();
//		System.out.println(c2);
//		sun.misc.Launcher$AppClassLoader@764c12b6
//		sun.misc.Launcher$ExtClassLoader@449b2d27
//		null
	}
}
