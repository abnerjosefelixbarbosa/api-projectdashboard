package com.api.projectdashboard.services.interfaces;

import com.api.projectdashboard.entities.ResponsibleEntity;

public interface ResponsibleInterface {
	ResponsibleEntity saveResponsible(ResponsibleEntity entity);
	ResponsibleEntity editResponsible(String id, ResponsibleEntity entity);
	ResponsibleEntity editResponsiblePassword(String id, ResponsibleEntity entity);
	ResponsibleEntity getResponsibleById(String id);
	void removeResponsibleById(String id);
}
