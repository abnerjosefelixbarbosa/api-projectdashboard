package com.api.projectdashboard.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	@Length(max = 100, message = "Name is max 100")
	private String name;
	@NotNull(message = "Date is null")
	@FutureOrPresent(message = "Date is invalid")
	private LocalDate startDate;
	@NotNull(message = "Date is null")
	@FutureOrPresent(message = "Date is invalid")
	private LocalDate endDate;
	@NotNull(message = "Budget is null")
	private BigDecimal budget;
	@NotNull(message = "Responsible id is null")
	private String responsibleId;
}