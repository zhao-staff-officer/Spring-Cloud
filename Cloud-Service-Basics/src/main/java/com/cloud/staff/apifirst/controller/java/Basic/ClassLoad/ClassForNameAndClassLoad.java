package com.cloud.staff.apifirst.controller.java.Basic.ClassLoad;

import java.lang.reflect.Method;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

/**
 * 探索源码解析
 * class.forName()与ClassLoad与。class
 * @author zhaoHB
 *
 */
public class ClassForNameAndClassLoad {
	
	//静态代码块 -执行时间-JVM初始化
	static {
		System.out.println("静态代码块");
	}
	
	//构造代码块 -执行时间-实例化对象时候执行
	{
		System.out.println("构造代码块");
	}
	//构造函数-执行时间-实例化对象,优先级小于构造代码块
	public ClassForNameAndClassLoad() {
		System.out.println("构造函数");
	}
	
	public static void main(String[] args) {
		try {
			Class<?> c=Class.forName("com.cloud.staff.apifirst.controller.java.Basic.ClassLoad.ClassForNameAndClassLoad");
			System.out.println(c.getClassLoader());
//			这里是applicationClassLoad-应用class加载器
//			sun.misc.Launcher$AppClassLoader@18b4aac2
//			Method[] method=c.getMethods();
			Method method=c.getMethod("invoekMethos", null);
//			方法.invoke(实例,参数)
			method.invoke(c.newInstance(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试方法调用
	 */
	public void invoekMethos() {
		System.out.println("invoke调用方法");
	}
	
//	class.forName(String classNmae)源码分析
//	 STEP:1 
//	   @CallerSensitive
//	    public static Class<?> forName(String className)
//	                throws ClassNotFoundException {
//	        Class<?> caller = Reflection.getCallerClass();
//	                 这里调用了forName0方法
//	        return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
//	    }
//	   
//	 STEP2:
//	   由于是native修饰看不到源码，我们参照 forName(String name, boolean initialize,ClassLoader loader)说明
//	 private static native Class<?> forName0(String name, boolean initialize,
//             ClassLoader loader,
//             Class<?> caller)
//   throws ClassNotFoundException;
//	
//	SETP:3
//	  name是引用全路径
//	* @param name       fully qualified name of the desired class
//	   当为true时候 class会执行初始化
//    * @param initialize if {@code true} the class will be initialized.
//    *                   See Section 12.4 of <em>The Java Language Specification</em>.

}
