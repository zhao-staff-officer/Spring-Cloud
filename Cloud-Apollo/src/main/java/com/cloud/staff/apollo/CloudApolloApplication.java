package com.cloud.staff.apollo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

@EnableApolloConfig
@SpringBootApplication
public class CloudApolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApolloApplication.class, args);
	}

}

