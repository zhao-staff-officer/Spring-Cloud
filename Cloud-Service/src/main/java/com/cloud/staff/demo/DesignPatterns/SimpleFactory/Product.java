package com.cloud.staff.demo.DesignPatterns.SimpleFactory;

/**
 * 简单工厂
 * 在创建一个对象时不向客户暴露内部细节，并提供一个创建对象的通用接口
 * 简单工厂把实例化的操作单独放到一个类中，这个类就成为简单工厂类，让简单工厂类来决定应该用哪个具体子类来实例化
 * @author zhaoHB
 *
 */
public interface Product{
    void operation();
}
