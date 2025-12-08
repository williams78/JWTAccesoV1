package com.home.control.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.home.control.interfaz.IJwt;
import com.home.control.interfaz.IUser;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final IJwt jwtService ;
	private final IUser userService;
	//private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
			                        @NonNull HttpServletResponse response,
			                        @NonNull FilterChain filterChain) throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		final String token;
		final String userEmail;
		
		if(StringUtils.isEmpty(authHeader) ||
				!StringUtils.startsWith(authHeader, "Bearer ")) {
			    filterChain.doFilter(request, response);
			    return;
		}
		
		token = authHeader.substring(7);
		userEmail = jwtService.extractUserName(token);
		
		if(StringUtils.isNoneEmpty(userEmail) &&
				SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userService.userDetailsService()
					.loadUserByUsername(userEmail);
			System.out.println(userDetails.getAuthorities());
			if(jwtService.isTokenValid(token, userDetails)) {
				
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
				
				System.out.println(userDetails.getAuthorities());
		    		
			}
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
