package com.ecommerce.UserModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = { "com.ecommerce.UserModule.controllers", "com.ecommerce.UserModule.entity",
		"com.ecommerce.UserModule.filters", "com.ecommerce.UserModule.repositry",
		"com.ecommerce.UserModule.securityConfig", "com.ecommerce.UserModule.services" })
public class UserModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserModuleApplication.class, args);
	}

}
