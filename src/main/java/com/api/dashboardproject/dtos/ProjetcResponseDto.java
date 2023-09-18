package com.api.dashboardproject.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.dashboardproject.entities.ProjectEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetcResponseDto {
	private String id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate;
	private BigDecimal budget;
	private String responsibleId;
	
	public ProjetcResponseDto(ProjectEntity entity) {
		this(entity.getId(), entity.getName(), entity.getStartDate(), entity.getEndDate(), entity.getBudget(),
				entity.getResponsibleEntity().getId());
	}
}