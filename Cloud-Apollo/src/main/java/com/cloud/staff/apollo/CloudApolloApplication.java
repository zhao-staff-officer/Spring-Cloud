package com.cloud.staff.apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@EnableApolloConfig
@EnableEurekaClient
@SpringBootApplication
public class CloudApolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApolloApplication.class, args);
	}

}

