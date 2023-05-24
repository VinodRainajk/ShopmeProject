package com.shopme.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopme.admin.Repositories.RoleRepositories;
import com.shopme.entities.userRole;

@Service
public class roleServices {
	
	@Autowired
	RoleRepositories roleRepositories;

	public roleServices(RoleRepositories roleRepositories) {
		super();
		this.roleRepositories = roleRepositories;
	}
	
	
	
	public List<userRole> getAllRoles()
	{
		return roleRepositories.findAll();
	}
}
