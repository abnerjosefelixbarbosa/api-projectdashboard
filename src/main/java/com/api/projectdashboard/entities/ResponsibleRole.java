package com.api.projectdashboard.entities;

public enum ResponsibleRole {
	ADMIN("admin"), USER("user");

	private String role;

	private ResponsibleRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
