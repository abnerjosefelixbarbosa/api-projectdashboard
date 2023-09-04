package com.api.dashboardproject.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResponsibleRequestDto  {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	private String name;
}
