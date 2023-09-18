package com.api.dashboardproject.dtos;

import com.api.dashboardproject.entities.ResponsibleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleResponseDto {
	private String id;
	private String name;
	private String login;
	private String password;
	
	public ResponsibleResponseDto(ResponsibleEntity entity) {
		this(entity.getId(),entity.getName(),entity.getLogin(), entity.getPassword());
	}
}