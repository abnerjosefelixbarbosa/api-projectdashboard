package com.api.projectdashboard.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.api.projectdashboard.dtos.ProjectRequestDto;

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

@Entity
@Table(name = "project_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private LocalDate startDate;
	@Column(nullable = false)
	private LocalDate endDate;
	@Column(nullable = false)
	private BigDecimal budget;
	@ManyToOne
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
