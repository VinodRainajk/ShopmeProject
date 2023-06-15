package com.shopme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Role_Shopme")
public class userRole {
	
	@Id
	@GeneratedValue
	public Integer id;
	
	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return RoleName;
	}

	public void setRoleName(String roleName) {
		RoleName = roleName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	String RoleName;
	
	String Description;

	@Override
	public String toString() {
		return RoleName;
	}

}
