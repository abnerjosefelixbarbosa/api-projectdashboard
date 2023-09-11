package com.api.dashboardproject.dtos;

import com.api.dashboardproject.entities.ResponsibleEntity;

public record ResponsibleResponseDto(String id, String name, String login, String password) {
	public ResponsibleResponseDto(ResponsibleEntity entity) {
		this(entity.getId(), entity.getName(), entity.getLogin(), entity.getPassword());
	}
}