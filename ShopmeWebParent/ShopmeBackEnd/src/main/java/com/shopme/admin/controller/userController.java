package com.shopme.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.admin.service.UserService;
import com.shopme.admin.service.roleServices;
import com.shopme.entities.userEntity;
import org.springframework.util.StringUtils;

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
		return getPagedUser(1, "firstName", "asc",model);
		//model.addAttribute("listUsers", userservice.getAllusers());
				
		//return "users";
		
	}
	
	@GetMapping("/users/page/{currentPage}")
	public String getPagedUser(@PathVariable("currentPage") int  pagenumber
									, @RequestParam(name = "sortField") String sortField
									 ,@RequestParam(name = "sortDir") String sortDir
									,Model model)
	{
		Page<userEntity> listofUsers = userservice.getUsers(pagenumber, sortField, sortDir);
		
		System.out.println("pagenumber "+ pagenumber);
		System.out.println("sortField "+ sortField);
		System.out.println("sortDir "+ sortDir);
		model.addAttribute("listUsers", listofUsers);
		model.addAttribute("currentPage", pagenumber);
		model.addAttribute("totalPages", listofUsers.getTotalPages()-1);
		model.addAttribute("startCount", pagenumber*5-4);
		model.addAttribute("endCount", pagenumber*5);
		model.addAttribute("totalItems", listofUsers.getTotalElements());
		//model.addAttribute("listUsers", userservice.getAllusers(pagenumber));
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		//model.addAttribute("reverseSortDir", reverseSortDir);
				
		return "users";
		
	}

	@GetMapping("/users/new")
	public String CreateUser(Model model)
	{
		System.out.println("Hello inside");
		//model.addAttribute("listUsers", userservice.getAllusers());
		userEntity user = new userEntity();
		model.addAttribute("user", user);
		model.addAttribute("listRoles",roleservice.getAllRoles());
		return "user_form";
		
	}
	
	@PostMapping("/users/save")
	public String SaveUser(userEntity user,RedirectAttributes redirectAttributes,@RequestParam("image") MultipartFile multipartFile)
	{
		System.out.println("PHOTO NAME "+ StringUtils.cleanPath(multipartFile.getOriginalFilename()));
		System.out.println("Photo Location "+ user.getPhotosImagePath());
		
		if(!multipartFile.isEmpty())
		{
			user.setPhotos(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
		}
		
		userEntity saveduser = userservice.saveUser(user,multipartFile);
		System.out.println("Record Saved "+ saveduser.getId());
	
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
		
		return "redirect:/users";
	}
	
	
	@GetMapping("/users/edit/{userid}")
	public String editUser(@PathVariable("userid") int  userid, Model model)
	{
		System.out.println("Hello inside edituser");
		userEntity user = userservice.getUserDetails(userid);
		model.addAttribute("user", user);
		model.addAttribute("listRoles",roleservice.getAllRoles());
		return "user_form";
		
	}
	
	@GetMapping("/users/delete/{userid}")
	public String DELETEUser(@PathVariable("userid") int  userid, Model model)
	{
		userservice.deleteUser(userid);
		return getPagedUser(1, "firstName", "asc",model);
		
	}


}