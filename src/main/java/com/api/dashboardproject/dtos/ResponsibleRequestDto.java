package com.api.dashboardproject.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ResponsibleRequestDto (
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name great than 100 characters")
	String name,
	@NotEmpty(message = "Login is empty")
	@NotNull(message = "Login is null")
	@Length(max = 100, message = "Login great than 100 characters")
	String login,
	@NotEmpty(message = "Password is empty")
	@NotNull(message = "Password is null")
	@Length(max = 100, message = "Passwor great than 100 characters")
	String password
) {}