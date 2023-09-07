package com.api.dashboardproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SegurityConfiguration {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests((configuration) -> {
			configuration.anyRequest().permitAll();
		})		
		.headers((val) -> val.disable())
		.sessionManagement((val) -> val.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.csrf((val) -> val.disable())
		.build();
	}
}
