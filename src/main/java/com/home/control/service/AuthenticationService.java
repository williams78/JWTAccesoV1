package com.home.control.service;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.home.control.entity.User;
import com.home.control.interfaz.IAuthentication;
import com.home.control.interfaz.IJwt;
import com.home.control.model.FieldsValuesS;
import com.home.control.model.Generales;
import com.home.control.model.JwtAuthenticationResponse;
import com.home.control.model.SignUpRequest;
import com.home.control.model.SigninRequest;
import com.home.control.repository.CustomRepository;
import com.home.control.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthentication{

	private final UserRepository userRepository;
	private final CustomRepository customRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwt jwtService;
    private final AuthenticationManager authenticationManager;
    private final FieldsValuesS object[] = new FieldsValuesS[1];
    private final Field[] Fieldsp = Generales.class.getDeclaredFields();
    
	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		
	

	object[0] = new FieldsValuesS(request.getNumberhome().toString(),Fieldsp[2].getName());
	Optional<String> p = customRepository.FindByRecordsString(object, Generales.class);	
	Optional<User> findby =userRepository.findByFiels(request.getUsername(), request.getEmail(), request.getPhone());
	
	if (p.isEmpty() && findby.isEmpty()) {
		
	var user = User.builder(). username(request.getUsername())
			    .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        
		User u = userRepository.save(user);
		
	var generales = Generales.builder(). gname(request.getNombre())
			.gnumberhome(request.getNumberhome())
			.iduser(u.getId())
			.build();
	
	customRepository.SaveRecord(generales);
	    
        var jwt = jwtService. generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
	}
	else {
		 
		
        return JwtAuthenticationResponse.builder().token(null)
        		.message(validation(findby,p,request))
        		.build();
	}
	}	
	

	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
	 			request.getUsername() , request.getPassword());
	
    	authenticationManager.authenticate(auth);
    	
        var user = userRepository.findByusername(request.getUsername())
        		.orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        	 
        
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
	}

	private String validation(Optional<User> user, Optional<String> p,SignUpRequest request) {
		
		String message="";
		
		if (!user.isEmpty()) {
			
			 if (user.get().getUsername().equals(request.getUsername())) {
				 message = " Usuario";
			 }
			 if (user.get().getEmail().equals(request.getEmail())) {
				 message = message + " Email";
			 }
			 if (user.get().getPhone().equals(request.getPhone())) {
				 message = message + " Celular";
			 }
		}
		
		if (!p.isEmpty()) {
			message =  message + " Número Casa";
		}
		
		
		return message;
	}
	
}
