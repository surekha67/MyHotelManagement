package com.ecommerce.UserModule.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerce.UserModule.filters.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	  private final AuthenticationProvider authenticationProvider;
	  private final JwtFilter jwtAuthFilter;

//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        return http
//	                .csrf(AbstractHttpConfigurer::disable)
//	                .authorizeHttpRequests(req ->
//	                        req.requestMatchers("/auth/*")
//	                                .permitAll()
//	                                .requestMatchers("/crackit/v1/management/**").hasAnyRole(ADMIN.name(), USER.name())
//	                                .requestMatchers(GET, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
//	                                .requestMatchers(POST, "/crackit/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
//	                                .anyRequest()
//	                                .authenticated())
//	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//	                .authenticationProvider(authenticationProvider)
//	                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//	                .build();
//	    }
	  
	    @Bean
	   	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   		return http
	   				.csrf(AbstractHttpConfigurer::disable)
	   				.authorizeHttpRequests(req->
	   				req.requestMatchers("/auth/**")
	   						.permitAll()
	   						.anyRequest()
	   						.authenticated())
	   				.build();
     }
}