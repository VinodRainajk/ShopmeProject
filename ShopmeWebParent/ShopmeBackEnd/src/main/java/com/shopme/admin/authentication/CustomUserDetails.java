package com.shopme.admin.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopme.entities.userEntity;
import com.shopme.entities.userRole;

public class CustomUserDetails implements  UserDetails {

	private userEntity userentity;
	
	
	 public CustomUserDetails(userEntity userentity) {
	        this.userentity = userentity;
	    }
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<GrantedAuthority> authorities  = new ArrayList<>();
		 for ( userRole role: userentity.getRoles()) {
		        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		    }
		    
		    return authorities;

	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		
		return userentity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userentity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

	public String getfullname() {
		// TODO Auto-generated method stub
		return this.userentity.getFirstName()+' '+ this.userentity.getLastName();
	}




}
