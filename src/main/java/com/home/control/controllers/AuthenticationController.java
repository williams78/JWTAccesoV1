package com.home.control.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.control.interfaz.IAuthentication;
import com.home.control.model.JwtAuthenticationResponse;
import com.home.control.model.SignUpRequest;
import com.home.control.model.SigninRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/acceso/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {


	private final IAuthentication authenticationService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request){
		
		return ResponseEntity.ok(authenticationService.signup(request));		
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request){
		return ResponseEntity.ok(authenticationService.signin(request));
	}
	
}
