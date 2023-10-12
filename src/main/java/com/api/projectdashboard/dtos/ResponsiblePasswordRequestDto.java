package com.api.projectdashboard.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsiblePasswordRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Password is null")
	@NotEmpty(message = "Password is empty")
	@Length(min = 20, message = "Password is min 20 length")
	@Length(max = 20, message = "Password is max 20 length")
	private String password;
}
