package com.cloud.staff.apifirst.config;





import java.lang.reflect.Method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.Target;
import feign.hystrix.HystrixFeign;
import feign.hystrix.SetterFactory;

/**
 * 
 * feignhystrix开启熔断
 * 1：fallback降级定义，快速失败，超时时间
 * 2：隔离：线程隔离
 * 3：熔断统计规则
 * 4：重试规则定义
 */
@Configuration
public class FeignHystrixConf {
	
	
	/**
	 * 开启熔断,使用hystrixFeign
	 * @return
	 */
	@Bean
	Feign.Builder feignBuilder(){
		return HystrixFeign.builder().setterFactory(new SetterFactory() {
			@Override
			public HystrixCommand.Setter create(Target<?> target, Method method) {
		      String groupKey = target.name();
		      String commandKey = Feign.configKey(target.type(), method);
		      return HystrixCommand.Setter
		          .withGroupKey(HystrixCommandGroupKey.Factory.asKey("apifirst"))
		          //.andCommandKey(HystrixCommandKey.Factory.asKey("apibase_Request"))
		          .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
		          .andCommandPropertiesDefaults(
		        		  HystrixCommandProperties.Setter()
		        		  
		        		  //是否启用降级处理，如果启用了，则在超时或异常时调用getFallback进行降级处理，默认开启。
		        		  .withFallbackEnabled(true)
		        		  //降级方法的最大调用线程数，如果超出此信号量，会抛出异常
		        		  .withFallbackIsolationSemaphoreMaxConcurrentRequests(10)
		        		  // 超时配置
		        		  .withExecutionTimeoutInMilliseconds(10000)
		        		  
		        		  //启用熔断,默认为true
		        		  .withCircuitBreakerEnabled(true)
		        		  //是否强制关闭熔断开关，如果强制关闭了熔断开关，则请求不会被降级，一些特殊场景可以动态配置该开关，默认为false
		        		  .withCircuitBreakerForceClosed(false)
		        		  //是否强制打开熔断开关，如果强制打开可熔断开关，则请求强制降级调用getFallback处理，可以通过动态配置来打开该开关实现一些特殊需求，默认为false。
		        		  .withCircuitBreakerForceOpen(false)
		        		  //如果在一个采样时间窗口内，失败率超过该配置，则自动打开熔断开关实现降级处理，即快速失败。默认配置下采样周期为10s，失败率为50%。
		        		  .withCircuitBreakerErrorThresholdPercentage(50)
		        		  //在熔断开关闭合情况下，在进行失败率判断之前，一个采样周期内必须进行至少N个请求才能进行采样统计，目的是有足够的采样使得失败率计算正确，默认为20。
		        		  .withCircuitBreakerRequestVolumeThreshold(20)
		        		  //熔断后的重试时间窗口，且在该时间窗口内只允许一次重试。即在熔断开关打开后，在该时间窗口允许有一次重试，如果重试成功，
		        		  //则将重置Health采样统计并闭合熔断开关实现快速恢复，否则熔断开关还是打开状态，执行快速失败。默认为为5s
		        		  .withCircuitBreakerSleepWindowInMilliseconds(5)
		        		  )
		          .andThreadPoolPropertiesDefaults(
		        		  HystrixThreadPoolProperties.Setter()
		        		      //允许线程池大小自动动态调整
		        		      .withAllowMaximumSizeToDivergeFromCoreSize(true)
		        		      //设置线程池大小，默认值是10
		        		      .withCoreSize(10)
		        		      //设置线程池的最大大小
		        		      .withMaximumSize(15)
		        		      //设置等待队列大小，默认值是5
		        		      .withMaxQueueSize(10)
		        		      //设置保持存活的时间，单位是分钟，默认是1
		        		      .withKeepAliveTimeMinutes(1)
		          );
		    }
		});
	}
	
	/**
	 * 设置feign日志
	 * NONE, 不记录日志 (默认)
	 * BASIC, 只记录请求方法和URL以及响应状态代码和执行时间
	 * HEADERS, 记录请求和应答的头的基本信息
	 * FULL, 记录请求和响应的头信息，正文和元数据
	 * @return
	 */
	@Bean
	Logger.Level loggerLevel(){
		return Logger.Level.FULL;
	}
	
	/**
	 * 设置feign读取超时10S，连接超时6S
	 * 
	 * param1:connectTimeoutMillis :10s 建立连接时间
	 * param2:readTimeoutMillis :6s 获取反馈时间
	 * @return
	 */
	@Bean
	Request.Options requestOptions(){
		return new Request.Options(10*1000, 6*1000);
	}
	/**
	 * 间隔算法参数，
	 * 最大重试间隔时间为1s,
	 * 重试次数为2次
	 * @return
	 */
	@Bean
    public Retryer feignRetryer() {
		//注： A:当请求时间大于Request.Options:readTimeoutMillis 时触发重试,即6s无返回则重试
		//   B:因为采用HystrixFeign，所以默认了hystrix熔断超时时间1s，应重新配置熔断超时；
		//     建立连接时间(Request.Options:connectTimeoutMillis)>熔断超时时间>readTimeoutMillis
        // return new Retryer.Default(100, SECONDS.toMillis(1), 2);
		//禁用重试,不重试,重试要考虑幂等性
		return Retryer.NEVER_RETRY;
    }
	
}
