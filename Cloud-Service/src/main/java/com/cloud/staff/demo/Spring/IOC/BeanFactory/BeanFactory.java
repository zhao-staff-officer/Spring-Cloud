package com.cloud.staff.demo.Spring.IOC.BeanFactory;

/**
 * BeanFactory接口
 *  ListableBeanFactory : 定义了访问容器中Bean的基本方法，入查看Bean的个数，获取某一类型Bean的配置名，查看容器中是否包含某一个Bean
 *  HierarchicalBeanFactory: 父子级联IOC容器接口，子容器可以通过接口方法访问父容器
 *  ConfigurableBeanFactory: 增强IOC的可定制性，它定义了设置类装载器，属性编辑器，容器初始化后置处理器等方法
 *  AutowireCapableBeanFactory: 定义了将容器中的Bean按照某种规则进行自动装配
 *  SingletonBeanRegistry: 定义了允许在允许期间向容器注册单实例Bean的方法
 */
public class BeanFactory {
}
