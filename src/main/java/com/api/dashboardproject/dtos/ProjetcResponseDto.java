package com.api.dashboardproject.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.dashboardproject.entities.ProjectEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/*
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjetcResponseDto {
	private String id;
	private String name;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private BigDecimal budget;
	private String responsibleId;
	
	public ProjetcResponseDto(ProjectEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
		this.budget = entity.getBudget();
		this.responsibleId = entity.getResponsibleEntity().getId();
	}
}
*/

public record ProjetcResponseDto(String id, String name, @JsonFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
		@JsonFormat(pattern = "yyyy-MM-dd") LocalDate endDate, BigDecimal budget, String responsibleId) {
	public ProjetcResponseDto(ProjectEntity entity) {
		this(entity.getId(), entity.getName(), entity.getStartDate(), entity.getEndDate(), entity.getBudget(),
				entity.getResponsibleEntity().getId());
	}
}