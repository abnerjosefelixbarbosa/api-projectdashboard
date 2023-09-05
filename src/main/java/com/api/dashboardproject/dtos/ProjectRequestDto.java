package com.api.dashboardproject.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectRequestDto {
	@NotEmpty(message = "Name is empty")
	@NotNull(message = "Name is null")
	private String name;
	@NotNull(message = "Date is null")
	@FutureOrPresent(message = "Date is invalid")
	private LocalDate startDate;
	@NotNull(message = "Date is null")
	@FutureOrPresent(message = "Date is invalid")
	private LocalDate endDate;
	@NotNull(message = "Budget is null")
	private BigDecimal budget;
	@NotEmpty(message = "Responsible id is empty")
	@NotNull(message = "Responsible id is null")
	private String responsibleId;
}
