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
public class ResponsibleSaveRequestDto {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name is max 100 length")
	private String name;
	@Email(message = "Email is invalid")
	private String email;
	@NotEmpty(message = "Password is empty")
	@NotNull(message = "Password is null")
	@Length(max = 20, message = "Password is max 20 length")
	private String password;
	@NotEmpty(message = "Role is empty")
	@NotNull(message = "Role is null")
	private String role;
}