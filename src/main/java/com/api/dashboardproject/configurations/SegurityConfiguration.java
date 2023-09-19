package com.api.dashboardproject.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.api.dashboardproject.interfaces.ResponsibleServiceInterface;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SegurityConfiguration {
	@Autowired
	private ResponsibleServiceInterface responsibleService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(val -> val.disable())
		    .authorizeHttpRequests(val -> {
		    	val.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll();
		    	val.requestMatchers(AntPathRequestMatcher.antMatcher("/responsibles/save")).permitAll();
		    	val.anyRequest().authenticated();
		    })
		    .sessionManagement(val -> val.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService  userDetailsService() {
		return responsibleService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		var authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
