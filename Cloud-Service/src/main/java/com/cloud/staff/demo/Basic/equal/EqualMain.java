package com.cloud.staff.demo.Basic.equal;

/**
 * 源码分析equal字段
 * @author zhaoHB
 *
 */
public class EqualMain {
	    public static void main(String[] args) {
			System.out.println(null instanceof String);
//			false
		}

//	    public boolean equals(Object anObject) {
//	                        判断对象地址是否相等，如果相等则直接返回true
//	        if (this == anObject) {
//	            return true;
//	        }
//	                          判断类型是否相等,这里被比较值=anobject会去空判断
//	                         一般 是把定量写外面，例如   “aaa”.equals(xxx),会先判断类型string类型，NULL instanceof String 不会通过 ,如上↑
//	        if (anObject instanceof String) {
//	            String anotherString = (String)anObject;
//	                                        字符串长度比较
//	            int n = value.length;
//	            if (n == anotherString.value.length) {
//	                                                      拆分 转换为数组table[i] 一位位比较，如有一位不匹配符则返回false
//	                char v1[] = value;
//	                char v2[] = anotherString.value;
//	                int i = 0;
//	                while (n-- != 0) {
//	                    if (v1[i] != v2[i])
//	                        return false;
//	                    i++;
//	                }
//	                return true;
//	            }
//	        }
//	        return false;
}
