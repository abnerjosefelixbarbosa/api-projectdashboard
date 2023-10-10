package com.api.projectdashboard.dtos;

import com.api.projectdashboard.entities.ResponsibleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleResponseDto {
	private String id;
	private String name;
	private String email;
	private String password;
	
	public ResponsibleResponseDto(ResponsibleEntity entity) {
		this(entity.getId(),entity.getName(),entity.getEmail(), entity.getPassword());
	}
}