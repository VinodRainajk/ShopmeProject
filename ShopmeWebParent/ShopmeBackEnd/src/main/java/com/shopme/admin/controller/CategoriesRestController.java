package com.shopme.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.admin.Repositories.CategoriesRepositories;

@RestController
public class CategoriesRestController {
	
	@Autowired
	CategoriesRepositories categoriesrepositories;
	
	@RequestMapping("/categories/check_unique")
	public String checkCategoryUnique( 
			@RequestParam(name = "name") String catName,
			@RequestParam(name = "alias") String catAlias)
	{
		
		 if(categoriesrepositories.findcategoryByNameAliasID(catName, catAlias).isPresent())
		 {
			 
			 if(categoriesrepositories.findcategoryByNameAliasID(catName, catAlias).get().getname() ==catName )
			 {
				return "DuplicateName"; 
			 } else
			 {
				 return "DuplicateAlias"; 
			 }
			 
		 }
		 return "OK";
	}

}
