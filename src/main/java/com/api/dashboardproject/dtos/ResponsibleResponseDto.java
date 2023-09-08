package com.api.dashboardproject.dtos;

import com.api.dashboardproject.entities.ResponsibleEntity;

/*
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibleResponseDto {
	private String id;
	private String name;
	
	public ResponsibleResponseDto(ResponsibleEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}
}
*/

public record ResponsibleResponseDto(String id, String name) {
	public ResponsibleResponseDto(ResponsibleEntity entity) {
		this(entity.getId(), entity.getName());
	}
}