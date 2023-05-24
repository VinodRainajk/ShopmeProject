package com.shopme.admin.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopme.entities.userEntity;
@Repository
public interface UserRepository extends JpaRepository<userEntity,Integer> 
{

}
