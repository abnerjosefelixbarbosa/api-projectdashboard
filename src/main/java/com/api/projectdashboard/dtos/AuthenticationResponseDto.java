package com.api.projectdashboard.dtos;

import com.api.projectdashboard.entities.ResponsibleEntity;

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
