package com.home.control.interfaz;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwt {

	String extractUserName(String token);
	String generateToken(UserDetails userDetails);
	boolean isTokenValid(String token, UserDetails userDetails);
	
}
