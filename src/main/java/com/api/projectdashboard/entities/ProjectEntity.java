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
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;
	@Column(name = "end_date", nullable = false)
	private LocalDate endDate;
	@Column(name = "budget", nullable = false, scale = 2)
	private BigDecimal budget;
	@ManyToOne
	@JoinColumn(name = "responsible_id", nullable = false)
	private ResponsibleEntity responsibleEntity;

	public ProjectEntity(ProjectRequestDto dto) {
		this(null, dto.getName(), dto.getStartDate(), dto.getEndDate(), dto.getBudget(), new ResponsibleEntity());
		this.responsibleEntity.setId(dto.getResponsibleId());
	}
}
