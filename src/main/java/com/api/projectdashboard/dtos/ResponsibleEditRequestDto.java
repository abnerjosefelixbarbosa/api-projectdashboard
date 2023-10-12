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
public class ResponsibleEditRequestDto {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name is max 100 length")
	private String name;
}
