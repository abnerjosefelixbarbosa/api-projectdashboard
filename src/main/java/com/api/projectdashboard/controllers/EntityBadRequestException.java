package com.api.projectdashboard.controllers;

public class EntityBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityBadRequestException(String message) {
		super(message);
	}
}
