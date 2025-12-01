package com.home.control.interfaz;

import com.home.control.model.JwtAuthenticationResponse;
import com.home.control.model.SignUpRequest;
import com.home.control.model.SigninRequest;

public interface IAuthentication {

	JwtAuthenticationResponse signup(SignUpRequest request);
	JwtAuthenticationResponse signin(SigninRequest request);
	
}
