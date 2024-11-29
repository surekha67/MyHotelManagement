package com.example.bookedroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.bookedroom.controller","com.example.bookedroom.entity","com.example.bookedroom.entity",
		"com.example.bookedroom.repository","com.example.bookedroom.service"})
public class BookedroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookedroomApplication.class, args);
	}

}
