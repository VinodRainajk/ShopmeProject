package com.shopme.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.shopme.admin.authentication.CustomUserDetailService;
@Configuration
@EnableWebSecurity
public class ShopmeConfiguration{
	
	 	@Bean
	    public UserDetailsService userDetailsService() {
	        return new CustomUserDetailService();
	    }
	 
	 
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	    	
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
//	        System.out.println("Password is "+ authProvider.get);
	        authProvider.setPasswordEncoder(encode());
	         
	        return authProvider;
	    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http
		    .authorizeHttpRequests(t ->
		                t.anyRequest()
		                .authenticated()
		            )
		    .formLogin(form ->
		            form
		            .loginPage("/login")
		            .loginProcessingUrl("/login")
		            .successHandler(successHandler())
		            .usernameParameter("email")
		            .passwordParameter("password")
		            .permitAll()
		                )
	/*	    .logout(out ->
		            out.logoutRequestMatcher(new 
		    AntPathRequestMatcher("/logout"))
		            .permitAll()
		            ) */
		    
		    .authenticationProvider(authenticationProvider()
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
	
	private AuthenticationSuccessHandler successHandler() {
	    SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
	    simpleUrlAuthenticationSuccessHandler.setDefaultTargetUrl("/");
	    return simpleUrlAuthenticationSuccessHandler;
	}
}
