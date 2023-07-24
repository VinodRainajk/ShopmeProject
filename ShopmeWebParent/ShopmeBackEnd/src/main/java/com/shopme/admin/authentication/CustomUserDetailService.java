package com.shopme.admin.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.shopme.admin.Repositories.UserRepository;
import com.shopme.entities.userEntity;


public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		
		System.out.println("CustomUserDetailService -->UserDetailsService-->loadUserByUsername -->"+username);
		userEntity userentity = userrepository.findByemail(username).get();
		System.out.println("user entity is " +userentity);
		
		 if (userentity == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
		return new CustomUserDetails(userentity);
	}

}
