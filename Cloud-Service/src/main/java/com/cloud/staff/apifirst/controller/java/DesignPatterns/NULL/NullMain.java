package com.cloud.staff.apifirst.controller.java.DesignPatterns.NULL;

/**
 * 设计模式-空对象
 * @author zhaoHB
 *使用什么都不做的空对象来代替 NULL。
 *一个方法返回 NULL，意味着方法的调用端需要去检查返回值是否是 NULL，这么做会导致非常多的冗余的检查代码。
 *并且如果某一个调用端忘记了做这个检查返回值，而直接使用返回的对象，那么就有可能抛出空指针异常。
 */
public class NullMain {
	
	public static void main(String[] args) {
        AbstractOperation abstractOperation = func(-1);
        abstractOperation.request();
    }

    public static AbstractOperation func(int para) {
        if (para < 0) {
            return new NullOperation();
        }
        return new RealOperation();
    }
//    do noting

}
