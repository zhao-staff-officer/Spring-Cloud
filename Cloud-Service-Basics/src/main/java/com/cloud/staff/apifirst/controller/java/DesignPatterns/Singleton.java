package com.cloud.staff.apifirst.controller.java.DesignPatterns;

/**
 * 单例模式<br/> 
 * 确保一个类只有一个实例，并提供该实例的全局访问点<br/>
 * 使用一个私有构造函数、一个私有静态变量以及一个公有静态函数来实现<br/>
 * 私有构造函数保证了不能通过构造函数来创建对象实例，只能通过公有静态函数返回唯一的私有静态变量。<br/>
 * @author zhaoHB
 * 有点：</br>
 * 系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能。</br>
 * 缺点：</br>
 * 当想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用new，可能会给其他开发人员造成困扰，特别是看不到源码的时候。</br>
 *
 */
public class Singleton {
	//1.懒汉模式-线程不安全
//	这个实现在多线程环境下是不安全的，如果多个线程能够同时进入 if (uniqueInstance == null) ，
//	并且此时 uniqueInstance 为 null，那么会有多个线程执行 uniqueInstance = new Singleton(); 
//	语句，这将导致实例化多次 uniqueInstance。
//	private static Singleton instance; --判断是否被实例化,返回函数
//	
//	private Singleton() {}  --私有构造方法，保证无法调用构造方法实例化，只能通过静态方法获取实例对象
//    
//	public static Singleton getInstance() {
//		if(instance==null) {
//			instance=new Singleton(); --实例化对象
//		}
//		return instance;
//	}
	//2.懒汉模式-线程安全
//	private static Singleton instance;
//	
//	private Singleton() {}
//	
//	public static synchronized Singleton getInstance() {
//		if(instance==null) {
//			instance=new Singleton();
//		}
//		return instance;
//	}
	
	//3.懒汉模式-双重校验锁
	//uniqueInstance 采用 volatile 关键字修饰也是很有必要的， uniqueInstance = new Singleton(); 这段代码其实是分为三步执行：
    //为 uniqueInstance 分配内存空间
    //初始化 uniqueInstance
    //将 uniqueInstance 指向分配的内存地址
    //但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1>3>2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。
	//例如，线程 T1 执行了 1 和 3，此时 T2 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化。
    //使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。
//	private volatile static Singleton instance;
//	
//	private Singleton(){}
//	
//	public static Singleton getInstance() {
//		if(instance==null) {
//			synchronized(Singleton.class) {
//				if(instance==null) {
//					instance=new Singleton();
//				}
//			}
//		}
//		return instance;
//	}
	
	//4.饿汉模式
//	private final static Singleton instance=new Singleton();
//	private Singleton() {}
//	public static Singleton getInstance() {
//		return instance;
//	}
	
	//5.静态内部类
	private Singleton() {}
	private static class SingleHolder{
		private static final Singleton instance=new Singleton();
	}
	public static Singleton getInstance() {
		return SingleHolder.instance;
	}
	
	//6.枚举模式
//	public enum SingletonDemo6 {
//	    instance;
//	    public void whateverMethod(){
//	    }
//	}
	
	
	//测试
	public static void main(String[] args) {
		Singleton sin1=new Singleton();
		System.out.println(sin1);
		Singleton sin2=new Singleton();
		System.out.println(sin2);
		Singleton sin3=Singleton.getInstance();
		System.out.println(sin3);
		Singleton sin4=Singleton.getInstance();
		System.out.println(sin4);
		
//		print 输出：
//		com.cloud.staff.apifirst.controller.java.DesignPatterns.Singleton@52d455b8
//		com.cloud.staff.apifirst.controller.java.DesignPatterns.Singleton@4f4a7090
//		com.cloud.staff.apifirst.controller.java.DesignPatterns.Singleton@18ef96
//		com.cloud.staff.apifirst.controller.java.DesignPatterns.Singleton@18ef96
		
	}
	
	
}
