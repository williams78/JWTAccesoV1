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
	
	String i = "";
	object[0] = new FieldsValuesS(request.getNumberhome().toString(),Fieldsp[2].getName());
	Optional<String> p = customRepository.FindByRecordsString(object, Generales.class);	
	Optional<User> findby =userRepository.findByFiels(request.getUsername(), request.getEmail(), request.getPhone());
	
	if (p.isEmpty() || findby.isEmpty()) {
		
	var user = User.builder(). username(request.getUsername())
			    .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        
		userRepository.save(user);
		
		Optional<User> userid = userRepository.findByusername(request.getUsername());
		
	var generales = Generales.builder(). gname(request.getNombre())
			.gnumberhome(request.getNumberhome())
			.iduser(userid.get().getId())
			.build();
	
	customRepository.SaveRecord(generales);
	
        var jwt = jwtService. generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
	}
	    if(request.getUsername().equals(findby.get().getUsername())) {
	    	i="1";
	    }
	    if(request.getEmail().equals(findby.get().getEmail())) {
	    	i=i+"2";
	    }
	    if(request.getPhone().equals(findby.get().getPhone())) {
	    	i=i+"3";
	    }
	    if(request.getNumberhome().equals(p.get())) {
	    	i=i+"4";
	    }
        return JwtAuthenticationResponse.builder().token(null).valid(i).build();
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

}
