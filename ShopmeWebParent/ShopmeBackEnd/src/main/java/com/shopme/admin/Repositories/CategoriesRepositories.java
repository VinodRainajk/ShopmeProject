package com.shopme.admin.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopme.entities.category;

@Repository
public interface CategoriesRepositories extends JpaRepository<category, Integer> {
	
	@Query("select u from category u where u.name =?1  or u.alias =?2  ")
	Optional<category> findcategoryByNameAliasID(String name, String alias);

	@Query("select u from category u where u.parent is null ")
	List<category> findrootCategory();
	
}
