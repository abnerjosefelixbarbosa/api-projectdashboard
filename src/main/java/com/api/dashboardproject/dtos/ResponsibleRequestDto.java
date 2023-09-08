package com.api.dashboardproject.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/*
@Data
public class ResponsibleRequestDto  {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name great than 100 characters")
	private String name;
}
*/

public record ResponsibleRequestDto (
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name great than 100 characters")
	String name
) {}