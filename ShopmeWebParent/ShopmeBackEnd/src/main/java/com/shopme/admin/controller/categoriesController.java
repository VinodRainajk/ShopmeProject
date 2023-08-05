package com.shopme.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.shopme.entities.category;
import com.shopme.admin.service.CategoriesService;

@Controller
public class categoriesController {
	
	@Autowired
	private CategoriesService categoriesService; 
	
	

	public categoriesController(CategoriesService categoriesService) {
		super();
		this.categoriesService = categoriesService;
	}
	

	@GetMapping("/categories")
	public String getCategories(Model model)
	{
		return getPagedCategories(1,"Alias","ASC",model);
			
	}
	
	@GetMapping("/categories/page/{currentPage}")
	public String getPagedCategories(@PathVariable("currentPage") int  pagenumber
									, @RequestParam(name = "sortField") String sortField
									 ,@RequestParam(name = "sortDir") String sortDir
									,Model model)
	{
		System.out.println("Listing out the Categories");
		List<category> listcategories=  categoriesService.getAllCategories();
		model.addAttribute("listCategories",listcategories);
		return "categories/categories";
	}
	

	@GetMapping("/categories/new")
	public String CreateCategories(Model model)
	{
		category newCategory =  new category();
		model.addAttribute("category",newCategory);
		return "categories/category_form";
	}
	
	
}
