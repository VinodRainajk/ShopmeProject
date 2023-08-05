package com.shopme.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopme.entities.category;
import com.shopme.admin.Repositories.CategoriesRepositories;

@Service
public class CategoriesService {
	
	@Autowired
	private CategoriesRepositories categoriesRepo; 
	
	public CategoriesService(CategoriesRepositories categoriesRepo)
	{
		this.categoriesRepo = categoriesRepo;
	}
	
	
	public List<category> getAllCategories()
	{
		return categoriesRepo.findAll();
	}
	
	

}
