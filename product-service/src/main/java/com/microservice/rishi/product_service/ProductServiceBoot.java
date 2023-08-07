package com.microservice.rishi.product_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Rishikesh
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ProductServiceBoot {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceBoot.class, args);
	}

}