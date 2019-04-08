package com.cloud.staff.apifirst.controller.java.Basic.copy;

/**
 * 深拷贝与浅拷贝
 * 浅拷贝：
 * ①对于数据类型是基本数据类型的成员变量，浅拷贝会直接进行值传递，也就是将该属性值复制一份给新的对象。
 * 因为是两份不同的数据，所以对其中一个对象的该成员变量值进行修改，不会影响另一个对象拷贝得到的数据。
 * ②对于数据类型是引用数据类型的成员变量，比如说成员变量是某个数组、某个类的对象等，那么浅拷贝会进行引用传递，
 * 也就是只是将该成员变量的引用值（内存地址）复制一份给新的对象。因为实际上两个对象的该成员变量都指向同一个实例。在这种情况下，在一个对象中修改该成员变量会影响到另一个对象的该成员变量值。
 * 
 * 深拷贝
 * 对于深拷贝来说，不仅要复制对象的所有基本数据类型的成员变量值，
 * 还要为所有引用数据类型的成员变量申请存储空间，并复制每个引用数据类型成员变量所引用的对象，
 * 直到该对象可达的所有对象。也就是说，对象进行深拷贝要对整个对象图进行拷贝！
 * 
 * API
 * 1:cglib版本：（使用动态代理，效率高）
 *   net.sf.cglib.beans.BeanCopier.copy(Object paramObject1, Object paramObject2, Converter paramConverter)
 * 2:Spring版本：（反射机制）
 *   org.springframework.beans.BeanUtils.copyProperties(Object source, Object target, Class editable, String[] ignoreProperties)
 *   新版本的spring也集成了cglib版：org.springframework.cglib.beans.BeanCopier
 * 3:Apache的两个版本：（反射机制）
 *   org.apache.commons.beanutils.PropertyUtils.copyProperties(Object dest, Object orig)
 *   org.apache.commons.beanutils.BeanUtils.copyProperties(Object dest, Object orig)
 * @author zhaoHB
 *
 */
public class CopyMain {
	public static void main(String[] args) {
		Student s=new Student("张三","10");
		Student s2=(Student)s.clone();
		System.out.println(s);
	    System.out.println(s2);
//	    com.cloud.staff.apifirst.controller.java.Basic.copy.Student@7852e922
//	    com.cloud.staff.apifirst.controller.java.Basic.copy.Student@4e25154f
	}

}
