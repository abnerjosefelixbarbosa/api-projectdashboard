package com.api.dashboardproject.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.api.dashboardproject.dtos.ProjectRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "project_tb")
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

	public ProjectEntity() {
		super();
	}

	public ProjectEntity(ProjectRequestDto dto) {
		super();
		this.name = dto.name();
		this.startDate = dto.startDate();
		this.endDate = dto.endDate();
		this.budget = dto.budget();
		this.responsibleEntity = new ResponsibleEntity();
		this.responsibleEntity.setId(dto.responsibleId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public ResponsibleEntity getResponsibleEntity() {
		return responsibleEntity;
	}

	public void setResponsibleEntity(ResponsibleEntity responsibleEntity) {
		this.responsibleEntity = responsibleEntity;
	}

	@Override
	public String toString() {
		return "ProjectEntity [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", budget=" + budget + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectEntity other = (ProjectEntity) obj;
		return Objects.equals(id, other.id);
	}
}
