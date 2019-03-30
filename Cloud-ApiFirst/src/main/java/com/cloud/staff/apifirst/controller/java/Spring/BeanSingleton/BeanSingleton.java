package com.cloud.staff.apifirst.controller.java.Spring.BeanSingleton;


public class BeanSingleton {
	//spring創建單利模式
//	BeanFactory Spring IOC容器的根接口
//
//	-- HierachicalBeanFactory 实现容器的继承,就是可以有父 BeanFactory
//
//	-- -- ConfigureabelBeanFactory 提供factory的配置功能
//
//	AliasRegistry 定义bean name的别名管理
//
//	-- SimpleAliasRegistry 在实现别名管理接口基础上,添加一个canonicalName查找类真是名称api
//
//	SingletonBeanRegistry 提供单例注册,查询服务
//
//	-- DefaultSingletonBeanRegistry 实现单例与DisposableBean的生命周期管理(创建,维护,销毁)
//
//	-- -- FactoryBeanRegistrySupport 添加工厂方式创建类FactoryBean的支持
//
//	-- -- -- AbstractBeanFactory BeanFactory的抽象实现.
	
	
//	源码分析：
//	
//	@Override
//	public void registerSingleton(String beanName, Object singletonObject) throws IllegalStateException {
//		super.registerSingleton(beanName, singletonObject);
//      --  判断是否已经创建过
//		if (hasBeanCreationStarted()) {  
//			// Cannot modify startup-time collection elements anymore (for stable iteration)
//	        --   维护beanDefinitionMap 实例-->ConcurrentHashMap<>(256)的表
//			synchronized (this.beanDefinitionMap) {
//				if (!this.beanDefinitionMap.containsKey(beanName)) { 
//                  ----扩充大小+1，manualSingletonNames维护的LinkedHashSet<>(16)表
//					Set<String> updatedSingletons = new LinkedHashSet<>(this.manualSingletonNames.size() + 1); 
//                  --数据转移，添加beanName
//					updatedSingletons.addAll(this.manualSingletonNames);
//					updatedSingletons.add(beanName);
//					this.manualSingletonNames = updatedSingletons;--存放
//				}
//			}
//		}
//		else {--已创建beanName
//			// Still in startup registration phase
//			if (!this.beanDefinitionMap.containsKey(beanName)) {--判断是否存在
//				this.manualSingletonNames.add(beanName);--不存在添加
//			}
//		}
//
//		clearByTypeCache();
//	}
	
	

}
