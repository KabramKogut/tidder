package com.tidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tidder.api.dto.CustomUserPrincipal;
import com.tidder.model.UserEntity;
import com.tidder.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = loginRepository.getUserWithAuthByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Email: " + email + " not found.");
		}
		return new CustomUserPrincipal(user);
	}

}
