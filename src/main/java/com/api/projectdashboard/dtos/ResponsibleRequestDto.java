package com.api.projectdashboard.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleRequestDto {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name great than 100 characters")
	private String name;
	@NotEmpty(message = "Login is empty")
	@NotNull(message = "Login is null")
	@Length(max = 100, message = "Login great than 100 characters")
	private String login;
	@NotEmpty(message = "Password is empty")
	@NotNull(message = "Password is null")
	@Length(max = 100, message = "Passwor great than 100 characters")
	private String password;
	@NotEmpty(message = "Role is empty")
	@NotNull(message = "Role is null")
	private String role;
}