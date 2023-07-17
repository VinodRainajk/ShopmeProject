package com.shopme.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration

public class ShopmeConfiguration{
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        // ...
        .authorizeHttpRequests(authorize -> authorize                                  
            .requestMatchers("**").permitAll()
			);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder encode()
	{
		return new BCryptPasswordEncoder();
	}



	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {

		return (web) -> web.ignoring()

				.requestMatchers("/images/**", "/js/**", "/webjars/**", "/fontawesome/**", "/webfonts/**", "/style.css");

	}
}
