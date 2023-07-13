package com.shopme.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class ShopmeConfiguration{
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        // ...
        .authorizeHttpRequests(authorize -> authorize                                  
            .requestMatchers("**").permitAll()
            .requestMatchers("/UserPhotos/*").permitAll() 
            .requestMatchers("/UserPhotos/*").permitAll() 
        );
		return http.build();
	}
	
	@Bean
	public PasswordEncoder encode()
	{
		return new BCryptPasswordEncoder();
	}

}
