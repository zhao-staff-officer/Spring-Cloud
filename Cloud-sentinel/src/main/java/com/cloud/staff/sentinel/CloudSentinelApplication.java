package com.cloud.staff.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudSentinelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudSentinelApplication.class, args);
	}

}
