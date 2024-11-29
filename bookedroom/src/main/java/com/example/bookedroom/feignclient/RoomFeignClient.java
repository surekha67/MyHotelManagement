package com.example.bookedroom.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bookedroom.entity.Room;

@FeignClient(name="room-service",path="/rooms")
public interface RoomFeignClient {
	
	@GetMapping("/getRoom/{id}") // Define the endpoint path
    Room getRoomById(@PathVariable Long id);

}
