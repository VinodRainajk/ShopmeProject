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
	Integer ID;
	
	String RoleName;
	
	String Description;

}
