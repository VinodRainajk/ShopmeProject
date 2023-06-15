package com.shopme.admin.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopme.entities.userEntity;
@Repository
public interface UserRepository extends JpaRepository<userEntity,Integer> 
{
	//public Page<userEntity> findAll(Pageable pageable);
	public Optional<userEntity> findByemail(String email);
	
}
