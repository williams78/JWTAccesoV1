package com.home.control.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.home.control.entity.User;
import com.home.control.interfaz.IAuthentication;
import com.home.control.interfaz.IJwt;
import com.home.control.model.JwtAuthenticationResponse;
import com.home.control.model.Residentes;
import com.home.control.model.SignUpRequest;
import com.home.control.model.SigninRequest;
import com.home.control.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthentication{

	private final UserRepository userRepository;
	
    private final PasswordEncoder passwordEncoder;
    private final IJwt jwtService;
    private final AuthenticationManager authenticationManager;
    
	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		
	


	Optional<User> findby =userRepository.findByFiels(request.getUsername(), request.getEmail(), request.getPhone());
	//p.isEmpty() &&
	if ( findby.isEmpty()) {
		
	    
	var residentes = Residentes.builder()
			.rsname(request.getNombre())
			.rsemail(request.getEmail())
			.rsphone(request.getPhone())
			.rsvivienda(request.getNumberhome())
			.build();
	
	var user = User.builder(). username(request.getUsername())
		    .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .usstatus(true)
            .residentes(residentes)
            .build();
					
		userRepository.save(user);

        var jwt = jwtService. generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
		}else {
        return JwtAuthenticationResponse.builder().token(null)
        		.message(validation(findby,request))
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

	private String validation(Optional<User> user,SignUpRequest request) {
		
		String message="";
		
		if (!user.isEmpty()) {
			
			 if (user.get().getUsername().equals(request.getUsername())) {
				 message = " Usuario";
			 }
			 if (user.get().getResidentes().getRsemail().equals(request.getEmail())) {
				 message = message + " Email";
			 }
			 if (user.get().getResidentes().equals(request.getPhone())) {
				 message = message + " Celular";
			 }
			 if (user.get().getResidentes().getRsvivienda().equals(request.getNumberhome())) {
				 message = message + " Número Casa";
			 }
		}		
		
		return message;
	}
	
}
