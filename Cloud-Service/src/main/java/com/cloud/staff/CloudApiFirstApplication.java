package com.cloud.staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages= {"com.cloud.staff.apifirst"})//指定扫描路径
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableCaching
@EnableSwagger2
public class CloudApiFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApiFirstApplication.class, args);
	}

}

