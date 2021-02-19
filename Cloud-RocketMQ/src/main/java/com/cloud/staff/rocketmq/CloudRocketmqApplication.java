package com.cloud.staff.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudRocketmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudRocketmqApplication.class, args);
	}

}
