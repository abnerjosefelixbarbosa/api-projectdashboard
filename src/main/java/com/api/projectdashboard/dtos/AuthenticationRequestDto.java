package com.api.projectdashboard.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {
	@NotEmpty(message = "Login is empty")
	@NotNull(message = "Login is null")
	private String login;
	@NotEmpty(message = "Password is empty")
	@NotNull(message = "Password is null")
	private String password;
}
