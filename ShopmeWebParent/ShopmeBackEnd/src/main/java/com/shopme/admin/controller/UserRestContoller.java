package com.shopme.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.admin.service.UserService;

@RestController
public class UserRestContoller {

	@Autowired
	UserService userservice;
	
	public UserRestContoller(UserService userservice) {
		super();
		this.userservice = userservice;
	}

	@PostMapping("/users/check_email")
	public String checkEmailUnique(@Param("email") String email, @Param("id") String id)
	{
		System.out.println("Inside check_email ");

		return userservice.checkEmailUnique(email,id);
	}
	
}
