package com.home.control.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.home.control.interfaz.IUser;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final IUser userService;
	
	@Bean
	SecurityFilterChain securityFilterCahin(HttpSecurity httpSecurity) throws Exception {
		
	
		httpSecurity.headers(header->header.frameOptions(frame->frame.disable()));
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		httpSecurity.cors(cor->cor.disable()).
					authorizeHttpRequests(request->request.requestMatchers("/api/acceso/auth/**").permitAll().
							requestMatchers("/api/acceso/admin/**").hasRole("ADMIN").
							requestMatchers("/api/inv/produc/**").hasAnyRole("USER","ADMIN").
							anyRequest().authenticated()).
					sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			         .authenticationProvider(authenticationProvider()).addFilterBefore(
			                 jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		 
		return httpSecurity.build();
		
	}

	@Bean
	 AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	 
	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		
		return config.getAuthenticationManager();
		
	}
}
