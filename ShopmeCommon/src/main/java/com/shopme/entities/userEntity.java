package com.shopme.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "User_Shopme")
public class userEntity {
		
		@Id
	    @GeneratedValue
		Integer Id; 
	    
	    @Column(nullable=false)	    
		String username;

		public List<userRole> getRoles() {
			return roles;
		}

		public void setRoles(List<userRole> roles) {
			this.roles = roles;
		}
		String password;
		String firstName;
		String lastName;
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}
		Date DOB;
		String photo;
		String email;
		Boolean enabled;
		
		@OneToMany
		@JoinTable(name="User_Role", joinColumns={@JoinColumn(name ="UserId", referencedColumnName ="Id")},
		inverseJoinColumns={@JoinColumn(name ="ID", referencedColumnName ="ID")})
		List<userRole> roles;
		
		public userEntity() {
			
		}
		
		public Integer getId() {
			return Id;
		}
		public void setId(Integer id) {
			Id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Date getDOB() {
			return DOB;
		}
		public void setDOB(Date dOB) {
			DOB = dOB;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}


}