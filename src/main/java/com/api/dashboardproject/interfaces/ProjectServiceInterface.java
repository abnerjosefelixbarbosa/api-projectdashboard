package com.api.dashboardproject.interfaces;

import com.api.dashboardproject.entities.ProjectEntity;

public interface ProjectServiceInterface extends ProjectInterface {
	void validateProject(ProjectEntity entity);
}
