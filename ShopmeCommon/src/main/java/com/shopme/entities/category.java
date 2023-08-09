package com.shopme.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class category {
	
	@Id
	@GeneratedValue
	private Integer id;
	

	private String name;
	
	private boolean Enabled;

	private String alias;
	
	
	private String image;
	
	public String getimage() {
		return image;
	}



	public void setimage(String image) {
		this.image = image;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	private category parent;
	
	@OneToMany(mappedBy="parent")
	private Set<category> childCategories =  new HashSet<category>();

	
	@Transient
	public String getImagePath() {
		return "/categoryPhotos/"+this.id+"/"+this.getimage();
	}
	
	
	
	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String categoryName) {
		name = categoryName;
	}

	public boolean getEnabled() {
		return Enabled;
	}





	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}



	public category getParent() {
		return parent;
	}

	public void setParent(category parentcategory) {
		this.parent = parentcategory;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}


	public Set<category> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<category> childCategories) {
		this.childCategories = childCategories;
	}

}
