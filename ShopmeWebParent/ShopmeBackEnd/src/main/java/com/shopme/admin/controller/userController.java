package com.shopme.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.service.UserService;
import com.shopme.admin.service.roleServices;
import com.shopme.entities.userEntity;


@Controller()
public class userController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	roleServices roleservice;

	public userController(UserService userservice,roleServices roleservice) {
		super();
		this.userservice = userservice;
		this.roleservice = roleservice;
	} 
	
	@GetMapping("/users")
	public String getAlluserDetails(Model model)
	{
		model.addAttribute("listUsers", userservice.getAllusers());
				
		return "users";
		
	}
	
	@GetMapping("/users/new")
	public String CreateUser(Model model)
	{
		//model.addAttribute("listUsers", userservice.getAllusers());
		userEntity user = new userEntity();
		model.addAttribute("user", user);
		model.addAttribute("listRoles",roleservice.getAllRoles());
		return "user_form";
		
	}
	
	@PostMapping("/users/save")
	public String SaveUser(userEntity user,RedirectAttributes redirectAttributes)
	{
		user.setUsername(user.getEmail() );
		userEntity saveduser = userservice.saveUser(user);
		System.out.println("Record Saved "+ saveduser.getId());
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
		
		return "redirect:/users";
	}
	
	


}