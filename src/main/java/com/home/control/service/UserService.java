package com.home.control.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.home.control.interfaz.IUser;
import com.home.control.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUser{

	private final UserRepository userRepository;
	
	@Override
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
				//return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Email no encontrado"));
				return userRepository.findByusername(username).orElseThrow(() -> new UsernameNotFoundException("Email no encontrado"));
				 
			}
		};
	}

}
