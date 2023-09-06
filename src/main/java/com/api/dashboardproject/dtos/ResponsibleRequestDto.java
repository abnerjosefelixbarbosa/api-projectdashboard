package com.api.dashboardproject.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResponsibleRequestDto  {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name great than 100 characters")
	private String name;
	@NotNull(message = "Email is null")
	@NotEmpty(message = "Email is empty")
	@Email(message = "Email is invalide")
	@Length(max = 50, message = "Name great than 50 characters")
	private String email;
	@NotEmpty(message = "Password is empty")
	@NotNull(message = "Password is null")
	@Length(min = 10, max = 50, message = "Password is invalide")
	private String password;
}
