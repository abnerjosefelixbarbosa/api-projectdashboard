package com.api.dashboardproject.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.dashboardproject.dtos.ProjectRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_tb")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;
	@Column(name = "budget", nullable = false, scale = 2)
	private BigDecimal budget;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "responsible_id", nullable = false)
	private ResponsibleEntity responsibleEntity;

	public ProjectEntity(ProjectRequestDto dto) {
		this.name = dto.getName();
		this.startDate = dto.getStartDate();
		this.endDate = dto.getEndDate();
		this.budget = dto.getBudget();
		this.responsibleEntity = new ResponsibleEntity();
		this.responsibleEntity.setId(dto.getResponsibleId());
	}
}
