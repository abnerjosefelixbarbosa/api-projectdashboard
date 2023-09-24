package com.api.projectdashboard.interfaces;

import com.api.projectdashboard.entities.ProjectEntity;

public interface ProjectServiceInterface extends ProjectInterface {
	void validateProject(ProjectEntity entity);
}
