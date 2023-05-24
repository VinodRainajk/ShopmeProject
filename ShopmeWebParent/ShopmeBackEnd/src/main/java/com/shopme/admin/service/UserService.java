package com.shopme.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.admin.Repositories.RoleRepositories;
import com.shopme.admin.Repositories.UserRepository;
import com.shopme.entities.userEntity;

@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepositories; 
	

	
	UserService(UserRepository userRepositories,RoleRepositories rolerepositories)
	{
		this.userRepositories = userRepositories;
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
	
	
	public List<userEntity> getAllusers()
	{
		
		return userRepositories.findAll();
		
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
	

	public userEntity saveUser(userEntity user)
	{
		return userRepositories.save(user);
	}
	
	public String checkEmailUnique(String email)
	{
		return "OK" ;
	}
	
}
