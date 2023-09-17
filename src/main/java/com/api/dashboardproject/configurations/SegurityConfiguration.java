package com.api.dashboardproject.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SegurityConfiguration {

	public void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((val) -> {
			val.anyRequest().authenticated();
		}).sessionManagement((val) -> val.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf((val) -> val.disable()).build();
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("123").roles("ADMIN");
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
