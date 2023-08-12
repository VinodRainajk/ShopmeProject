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
	


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	private category parent;
	
	@OneToMany(mappedBy="parent")
	private Set<category> childCategories =  new HashSet<category>();

	
	@Transient
	public String getImagePath() {
		return "/categoryPhotos/"+this.id+"/"+this.getImage();
	}
	
	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@Transient
	private boolean hasChildren;

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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
