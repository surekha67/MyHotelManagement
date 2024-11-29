package com.ecommerce.UserModule.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.UserModule.entity.AuthenticationRequest;
import com.ecommerce.UserModule.entity.AuthenticationResponse;
import com.ecommerce.UserModule.entity.RegisterRequest;
import com.ecommerce.UserModule.entity.User;
import com.ecommerce.UserModule.repositry.UserRepositry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	 
    private  final UserRepositry userRepository;
    private  final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        var savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
				
	}
    
    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //FirstStep
            //We need to validate our request (validate whether password & username is correct)
            //Verify whether user present in the database
            //Which AuthenticationProvider -> DaoAuthenticationProvider (Inject)
            //We need to authenticate using authenticationManager injecting this authenticationProvider
        //SecondStep
            //Verify whether userName and password is correct => UserNamePasswordAuthenticationToken
            //Verify whether user present in db
            //generateToken
            //Return the token
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();

    }
    
    public User getUserByEmail(String email) {
    	logger.info("Fetching user by email: {}", email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
