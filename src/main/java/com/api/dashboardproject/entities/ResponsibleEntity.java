package com.api.dashboardproject.entities;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@OneToMany(mappedBy = "responsibleEntity")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ProjectEntity> projectEntities;
	
	public ResponsibleEntity(ResponsibleRequestDto dto) {
		this.name = dto.name();
	}
}
