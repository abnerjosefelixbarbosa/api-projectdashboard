package com.api.dashboardproject.entities;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "responsible_tb")
public class ResponsibleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	public ResponsibleEntity(ResponsibleRequestDto dto) {
		this.name = dto.getName();
	}
}
