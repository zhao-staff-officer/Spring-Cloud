package com.cloud.staff.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CloudRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudRabbitApplication.class, args);
	}

}

