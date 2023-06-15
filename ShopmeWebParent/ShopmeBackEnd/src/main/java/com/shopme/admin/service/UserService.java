package com.shopme.admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopme.admin.Repositories.RoleRepositories;
import com.shopme.admin.Repositories.UserRepository;
import com.shopme.entities.userEntity;

import jakarta.servlet.ServletContext;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepositories; 
	
	@Autowired
	private final PasswordEncoder passwordencoder;
	
	@Autowired
	private final Environment environment;
	
	@Autowired
	ServletContext context;
	

	
	UserService(UserRepository userRepositories
			,RoleRepositories rolerepositories
			,PasswordEncoder passwordencoder,
			Environment environment)
	{
		this.userRepositories = userRepositories;
		this.passwordencoder = passwordencoder;
		this.environment = environment;
	}
	
	public userEntity getUserDetails(Integer Userid)
	{
		
		Optional<userEntity> optentity = userRepositories.findById(Userid);
		
		if(optentity.isPresent())
		{
			return optentity.get();
		}
		
		return null;
	}
	
	
	public Page<userEntity> getUsers(int pagenumber, String sortfiled, String SortDirection)
	{
		//
		
		 Direction direction = SortDirection.equals("ASC")? Sort.Direction.ASC: Sort.Direction.DESC ;
		 Order order = new Order(direction,sortfiled);
		 List<Order> orderlist = new ArrayList<>();
		 orderlist.add(order); 
		 Integer resultperpage = Integer.valueOf(environment.getProperty("shopme.resultperpage"));
		 Pageable paging = PageRequest.of(pagenumber, resultperpage,Sort.by(orderlist));
		return userRepositories.findAll(paging);
		
	}
	
	
	public userEntity updateUser(userEntity modifiedValue)
	{
		Optional<userEntity> newvalue =  userRepositories.findById(modifiedValue.getId());
		newvalue.ifPresent((usrval)-> {usrval.setRoles(modifiedValue.getRoles());
		usrval.setFirstName(modifiedValue.getFirstName());
		usrval.setLastName(modifiedValue.getLastName());});	
		newvalue.ifPresent( userRepositories::save);
		return newvalue.get();
	}
	
	
	public void deleteUser(Integer Userid)
	{
		 userRepositories.deleteById(Userid);
	
	}

	public userEntity saveUser(userEntity user,MultipartFile file)
	{
		System.out.println("before Password "+user.getPassword());
		
	
		
		user.setPassword(passwordencoder.encode(user.getPassword()));
		System.out.println("Encoded Password "+user.getPassword());
		
		userEntity savedUser = userRepositories.save(user);
			try {
				if(SaveUserPhotos(savedUser,file)) {
						
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return savedUser;
	}
	
	public String checkEmailUnique(String email,String id)
	{
	
		Optional<userEntity> optionalUser = userRepositories.findByemail(email);
		System.out.println("optionalUser ");
		
		if(optionalUser.isPresent())
		{
			String derivedid =  optionalUser.get().getId().toString();
			System.out.println("derivedid "+derivedid);
			
			if(id!= null && !id.equals(derivedid))
					{
				
						return "Duplicated";
					}
			
		}
		
		return "OK" ;
	}
	
	
	public boolean SaveUserPhotos(userEntity user,MultipartFile file) throws IOException
	{
		  String uploadDir = "UserPhotos/" + user.getId();
		  Path path = Paths.get(uploadDir);
		 
		  if(!Files.exists(path))
		  {
				Files.createDirectories(path);
		  
		  }
		  
		  Path filepath =  path.resolve(user.getPhotos());
		  System.out.println("File Path is "+filepath);
		  
		  Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);

		return true;
	}
	
}
