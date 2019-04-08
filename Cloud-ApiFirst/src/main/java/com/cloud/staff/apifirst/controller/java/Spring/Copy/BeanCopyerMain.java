package com.cloud.staff.apifirst.controller.java.Spring.Copy;

import org.springframework.beans.BeanUtils;

import com.cloud.staff.apifirst.controller.java.Basic.copy.Student;


public class BeanCopyerMain {
	public static void main(String[] args) {
		
		ProductDTO pdt = new ProductDTO();
        pdt.setIsConsignerCreditLimit(3);
        pdt.setLoanAction("323f3");
        pdt.setProductName("feiwefe");
        pdt.setPoundageRate("f332r3");
        pdt.setStudent(new Student("张三","10"));
        
        ProductDTO pdt2 = new ProductDTO();
        BeanUtils.copyProperties(pdt, pdt2);
	}
}
//        楼下源码解析
        
        /**
         * 翻译过来：将给定源bean的属性值复制到给定的目标bean中
    	 * Copy the property values of the given source bean into the given target bean.
    	 * 
    	 * <p>Note: The source and target classes do not have to match or even be derived
    	 * from each other, as long as the properties match. Any bean properties that the
    	 * source bean exposes but the target bean does not will silently be ignored.
    	 * @param source the source bean 拷贝对象
    	 * @param target the target bean 目标对象
    	 * @param editable the class (or interface) to restrict property setting to
    	 * @param ignoreProperties array of property names to ignore
    	 * @throws BeansException if the copying failed
    	 * @see BeanWrapper
    	 */
//    	private static void copyProperties(Object source, Object target, @Nullable Class<?> editable,
//    			@Nullable String... ignoreProperties) throws BeansException {
//
//    		Assert.notNull(source, "Source must not be null");
//    		Assert.notNull(target, "Target must not be null");
//          获取目标bean-class对象
//    		Class<?> actualEditable = target.getClass();
//    		if (editable != null) {
//    			if (!editable.isInstance(target)) {
//    				throw new IllegalArgumentException("Target class [" + target.getClass().getName() +
//    						"] not assignable to Editable class [" + editable.getName() + "]");
//    			}
//    			actualEditable = editable;
//    		}
//          获取目标对象属性
//    		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
//    		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
//
//    		for (PropertyDescriptor targetPd : targetPds) {
//              获取目标对象set方法
//    			Method writeMethod = targetPd.getWriteMethod();
//    			if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
//                  获取拷贝对象属性
//    				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
//    				if (sourcePd != null) {
//                      获取拷贝对象get方法
//    					Method readMethod = sourcePd.getReadMethod();
//    					if (readMethod != null &&
//                               拷贝对象get方法与目标对象set方法匹配上
//    							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
//    						try {
//                              修改作用域
//    							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
//    								readMethod.setAccessible(true);
//    							}
//                                获取拷贝对象值
//    							Object value = readMethod.invoke(source);
//                                修改作用域
//    							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
//    								writeMethod.setAccessible(true);
//    							}
//                                目标对象反射赋值
//    							writeMethod.invoke(target, value);
//    						}
//    						catch (Throwable ex) {
//    							throw new FatalBeanException(
//    									"Could not copy property '" + targetPd.getName() + "' from source to target", ex);
//    						}
//    					}
//    				}
//    			}
//    		}
//    	}
//        
//	}

