package com.api.projectdashboard.interfaces;

import com.api.projectdashboard.entities.ResponsibleEntity;

public interface TokenServiceInterface {
	public String generateToken(ResponsibleEntity entity);
	public String validateToken(String token);
}
