package com.cloud.staff.apifirst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages= {"com.cloud.staff.apifirst","com.cloud.staff.common"})
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableCaching
@EnableSwagger2
@MapperScan("com.cloud.staff.apifirst.dao")
public class CloudApiFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApiFirstApplication.class, args);
	}

}

