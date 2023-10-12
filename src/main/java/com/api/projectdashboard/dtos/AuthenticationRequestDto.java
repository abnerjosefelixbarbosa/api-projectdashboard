package com.api.projectdashboard.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {
	@Email(message = "Email invalid")
	private String email;
	@NotEmpty(message = "Password is empty")
	@NotNull(message = "Password is null")
	@Length(min = 20, message = "Password is min 20 length")
	private String password;
}
