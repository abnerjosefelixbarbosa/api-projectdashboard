package com.api.dashboardproject.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SegurityConfiguration {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests((val) -> {
			val.anyRequest().authenticated();
		}).sessionManagement((val) -> val.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf((val) -> val.disable()).build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		var admin = User.withUsername("A1111").password(passwordEncoder().encode("A1111")).roles("ADMIN").build();
		var user1 = User.withUsername("B1111").password(passwordEncoder().encode("B1111")).roles("USER1").build();
		var user2 = User.withUsername("C1111").password(passwordEncoder().encode("C1111")).roles("USER2").build();
		return new InMemoryUserDetailsManager(admin, user1, user2);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
