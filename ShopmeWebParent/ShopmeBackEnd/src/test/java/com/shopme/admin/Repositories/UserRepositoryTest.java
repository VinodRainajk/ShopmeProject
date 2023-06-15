package com.shopme.admin.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.annotation.Rollback;

import com.shopme.entities.userEntity;



@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepositories; 
	
	@Autowired
	RoleRepositories rolerepositories; 
	
	@Test
	public void addnewuser()
	{
		userEntity userentity = new userEntity();
		userentity.setFirstName("Vinod");
		userentity.setLastName("Raina");
		userEntity userentity2 =  userRepositories.save(userentity);
		System.out.println("userentity2 "+userentity2.getId());
		assertThat(userentity2.getId()).isGreaterThan(0);

	}


}
