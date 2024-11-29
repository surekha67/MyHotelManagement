package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	 @Bean
	    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
	        return builder.routes()
	                .route("room_route", r -> r.path("/rooms/**").uri("http://localhost:8093"))
	                .route("bookedroom_route", r -> r.path("/bookedrooms/**").uri("http://localhost:8091"))
	                .route("role_route", r -> r.path("/roles/**").uri("http://localhost:8092"))
	                .route("rating_route", r -> r.path("/ratings/**").uri("http://localhost:8097"))
	                .route("user_route", r -> r.path("/auth/**").uri("http://localhost:8088"))
	                .build();
	}
}
