package com.cloud.staff.apisecond;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages= {"com.cloud.staff.apisecond","com.cloud.staff.common"})//指定扫描路径
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableCaching
@EnableSwagger2
@MapperScan("com.cloud.staff.apisecond.dao")
public class CloudApiSecondApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApiSecondApplication.class, args);
	}

}

