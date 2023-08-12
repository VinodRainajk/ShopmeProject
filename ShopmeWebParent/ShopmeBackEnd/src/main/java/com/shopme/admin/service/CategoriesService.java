package com.shopme.admin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopme.entities.category;
import com.shopme.entities.userEntity;
import com.shopme.admin.Repositories.CategoriesRepositories;

@Service
public class CategoriesService {
	
	public static final int ROOT_CATEGORIES_PER_PAGE = 1;
	
	@Autowired
	private CategoriesRepositories categoriesRepo; 
	
	public CategoriesService(CategoriesRepositories categoriesRepo)
	{
		this.categoriesRepo = categoriesRepo;
	}
	
	
	public category saveCategory(category Category,MultipartFile multipartFile)
	{
		category SavedCategory  = categoriesRepo.save(Category);
		
		if(!multipartFile.getName().isEmpty())
		{
			try {
				SaveCategoryPhotos( SavedCategory, multipartFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return SavedCategory;
	}
	
	
	public Page<category> getAllCategories(String sortDir, String sortField, int pagenumber)
	{
		 Direction direction = sortDir.equals("ASC")? Sort.Direction.ASC: Sort.Direction.DESC ;
		 Order order = new Order(direction,sortField);
		 List<Order> orderlist = new ArrayList<>();
		 orderlist.add(order); 
		 Integer resultperpage = ROOT_CATEGORIES_PER_PAGE;
		 Pageable paging = PageRequest.of(pagenumber, resultperpage,Sort.by(orderlist)); 
		 Page<category> returnpage = categoriesRepo.findAll(paging);
		
		return returnpage;
	}
	
	
	public boolean SaveCategoryPhotos(category Category,MultipartFile file) throws IOException
	{
		
		  String uploadDir = "categoryPhotos/" + Category.getId();
		  Path path = Paths.get(uploadDir);
		 
		  if(!Files.exists(path))
		  {
				Files.createDirectories(path);
		  
		  }
		  
		  Path filepath =  path.resolve(Category.getImage());
		  System.out.println("File Path is "+filepath);
		  
		  Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

		return true;
	}
	

}
