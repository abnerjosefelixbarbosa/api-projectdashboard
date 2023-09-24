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
	@NotEmpty(message = "login is empty")
	@NotNull(message = "login is null")
	private String login;
	@NotEmpty(message = "password is empty")
	@NotNull(message = "password is null")
	private String password;
}
