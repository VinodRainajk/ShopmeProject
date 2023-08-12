package com.shopme.admin.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName = "UserPhotos";
		Path userPhotosDir = Paths.get(dirName);
		Path CategoreiesPhotosDir = Paths.get("categoryPhotos");
		
		
		String userPhotosPath = userPhotosDir.toFile().getAbsolutePath();
		System.out.println("path is "+userPhotosPath);
		System.out.println("dirName is "+dirName);
		registry.addResourceHandler("/" + dirName + "/**")
			.addResourceLocations("file:/" + userPhotosPath + "/");
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
		registry.addResourceHandler("/" + "categoryPhotos" + "/**")
		.addResourceLocations("file:/" + CategoreiesPhotosDir.toFile().getAbsolutePath() + "/");
	        WebMvcConfigurer.super.addResourceHandlers(registry);
	}


	
}
