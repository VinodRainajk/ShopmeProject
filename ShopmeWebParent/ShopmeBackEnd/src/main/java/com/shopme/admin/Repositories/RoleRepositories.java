package com.shopme.admin.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopme.entities.userRole;

@Repository
public interface RoleRepositories extends JpaRepository<userRole, Integer> {

}
