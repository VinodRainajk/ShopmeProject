package com.shopme.admin.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopme.entities.category;

@Repository
public interface CategoriesRepositories extends JpaRepository<category, Integer> {

}
