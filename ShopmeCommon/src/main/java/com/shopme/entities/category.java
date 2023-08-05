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
	
	@Column(length = 64, nullable = false, unique = true)
	private String alias;
	
	@Column(length = 128, nullable = false)
	private String image;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent")
	private category parent;
	
	@OneToMany(mappedBy="parent")
	private Set<category> childCategories =  new HashSet<category>();

	
	@Transient
	public String getImagePath() {
		return "/categoryPhotos/"+this.id+"/"+this.getImage();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
