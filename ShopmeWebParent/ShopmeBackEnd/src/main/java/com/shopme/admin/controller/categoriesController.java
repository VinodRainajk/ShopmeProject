package com.shopme.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class categoriesController {

	@GetMapping("/categories")
	public String getCategories(Model model)
	{
		
		return "categories/categories";
		
	}
}
