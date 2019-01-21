package com.cloud.staff.fastdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudFastDfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudFastDfsApplication.class, args);
	}

}

