package com.api.dashboardproject.dtos;

import com.api.dashboardproject.entities.ResponsibleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibleResponseDto {
	private String id;
	private String name;
	private String email;
	private String password;
	
	public ResponsibleResponseDto(ResponsibleEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.password = entity.getPassword();
	}
}
