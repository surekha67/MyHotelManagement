package com.hotel.UserModule.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.UserModule.entity.AuthenticationRequest;
import com.ecommerce.UserModule.entity.AuthenticationResponse;
import com.ecommerce.UserModule.entity.RegisterRequest;
import com.ecommerce.UserModule.entity.User;
import com.ecommerce.UserModule.services.UserService;

import lombok.RequiredArgsConstructor;
@CrossOrigin("http://localhost:3000")
//@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
	
	 private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
//    @CrossOrigin("*")
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register
					(@RequestBody RegisterRequest registerRequest)
	{
		AuthenticationResponse authResponse = userService.register(registerRequest);
		return ResponseEntity.ok(authResponse);
	}
	
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
       return ResponseEntity.ok(userService.authenticate(request));
    }
    @GetMapping("/getByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
    	logger.info("Fetching all users : {}");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
