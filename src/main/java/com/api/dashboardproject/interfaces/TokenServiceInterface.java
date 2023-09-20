package com.api.dashboardproject.interfaces;

import com.api.dashboardproject.entities.ResponsibleEntity;

public interface TokenServiceInterface {
	public String generateToken(ResponsibleEntity entity);
	public String validateToken(String token);
}
