package com.api.dashboardproject.dtos;

import com.api.dashboardproject.entities.ResponsibleEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {
	private String id;
	private String name;
	private String login;
	private String password;
	private String token;
	
	public AuthenticationResponseDto(ResponsibleEntity entity, String token) {
		this (entity.getId(), entity.getName(), entity.getLogin(), entity.getPassword(), token);
	}
}
