package com.api.projectdashboard.configurations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.projectdashboard.repositories.ResponsibleRepository;
import com.api.projectdashboard.services.interfaces.TokenServiceInterface;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	 @Autowired
	 private TokenServiceInterface tokenService;
	 @Autowired
	 private ResponsibleRepository responsibleRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var token = this.recoverToken(request);
		
        if(token != null){
            var email = tokenService.validateToken(token);
            UserDetails user = responsibleRepository.findByEmail(email).orElseThrow(() -> {
    			throw new UsernameNotFoundException("Login not found");
    		});

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
	}
	 
	
	private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) 
        	return null;
        return authHeader.replace("Bearer ", "");
    }
}
