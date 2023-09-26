package com.api.projectdashboard.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.api.projectdashboard.interfaces.ResponsibleServiceInterface;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SegurityConfiguration {
	@Autowired
	private ResponsibleServiceInterface responsibleService;
	@Autowired
	private SecurityFilter securityFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf((val) -> { 
		    val.disable();
		})
		.authorizeHttpRequests((val) -> {
			val.requestMatchers(AUTH_WHITELIST).permitAll();
			val.anyRequest().authenticated();
		})
		.sessionManagement((val) -> val.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.httpBasic(Customizer.withDefaults())
		.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
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

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
	
	private static final AntPathRequestMatcher[] AUTH_WHITELIST = {
			AntPathRequestMatcher.antMatcher("/api/v1/auth/**"),
			AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
			AntPathRequestMatcher.antMatcher("/v3/api-docs.yaml"),
			AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
			AntPathRequestMatcher.antMatcher("/swagger-ui.html"),
			AntPathRequestMatcher.antMatcher("/responsibles/save"),
			AntPathRequestMatcher.antMatcher("/responsibles/login")
	};
}
