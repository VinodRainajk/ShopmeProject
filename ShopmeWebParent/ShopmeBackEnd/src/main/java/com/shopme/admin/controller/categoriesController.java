package com.shopme.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopme.entities.category;
import com.shopme.entities.userEntity;
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
		System.out.println("Listing out the pagenumber "+pagenumber);
		Page<category>  listcategories=  categoriesService.getAllCategories( sortDir,  sortField,pagenumber-1);
		long startCount = (pagenumber - 1) * categoriesService.ROOT_CATEGORIES_PER_PAGE + 1;
		long endCount = startCount + categoriesService.ROOT_CATEGORIES_PER_PAGE - 1;
		if (endCount > listcategories.getTotalElements()) {
			endCount = listcategories.getTotalElements();
		}
		model.addAttribute("listCategories",listcategories);
		model.addAttribute("totalPages", listcategories.getTotalPages());
		model.addAttribute("totalItems", listcategories.getTotalElements());
		model.addAttribute("currentPage", pagenumber);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		//model.addAttribute("keyword", keyword);
		//model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", listcategories.getTotalPages());		
		//model.addAttribute("reverseSortDir", reverseSortDir);
		
		return "categories/categories";
	}
	

	@GetMapping("/categories/new")
	public String CreateCategories(Model model)
	{
		category newCategory =  new category();
		model.addAttribute("category",newCategory);
		//model.addAttribute("listCategories", listCategories);
		model.addAttribute("pageTitle", "Create New Category");
		return "categories/category_form";
	}
	
	@PostMapping("/categories/save")
	public String CreateCategories(category categoryentity,RedirectAttributes redirectAttributes,@RequestParam("fileImage") MultipartFile multipartFile)
	{
		System.out.println("Before saving  the Catrgory "+categoryentity.getAlias());
		//System.out.println("Before saving  the imagename "+categoryentity.getimage());
		System.out.println("Before saving  the multipartFile "+multipartFile.getName());
		
		
		categoryentity.setImage(multipartFile.getName());
		category savedCategory = categoriesService.saveCategory(categoryentity,multipartFile);
		//System.out.println("Saved the Catrgory "+savedCategory.getid());
		redirectAttributes.addFlashAttribute("message", "The Category has been saved successfully.");
		return "redirect:/category_form";
		//return "categories/category_form";
	}
	
	
}
