package com.api.dashboardproject.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ProjectRequestDto {
	private String id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private BigDecimal budget;
	private String responsibleId;
}
