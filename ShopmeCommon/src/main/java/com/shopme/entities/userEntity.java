package com.shopme.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "User_Shopme")
public class userEntity {
		
		@Id
	    @GeneratedValue
		Integer Id; 
	    


		public List<userRole> getRoles() {
			return roles;
		}

		public void setRoles(List<userRole> roles) {
			this.roles = roles;
		}
		String password;
		String firstName;
		String lastName;
		String photosImagePath;
		
		@Transient 
		public String getPhotosImagePath() {
			 return "/UserPhotos/" + this.Id + "/" + this.photos;
		}

		public void setPhotosImagePath(String photosImagePath) {
			this.photosImagePath = photosImagePath;
		}

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
		String photos;
		String email;
		Boolean enabled;
		
		@ManyToMany
		@JoinTable(name="User_Role", joinColumns={@JoinColumn(name ="UserId", referencedColumnName ="Id")},
		inverseJoinColumns={@JoinColumn(name ="id", referencedColumnName ="id")})
		List<userRole> roles;
		
		public userEntity() {
			
		}
		
		public Integer getId() {
			return Id;
		}
		public void setId(Integer id) {
			Id = id;
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
		public String getPhotos() {
			return photos;
		}
		public void setPhotos(String photos) {
			this.photos = photos;
		}


}
