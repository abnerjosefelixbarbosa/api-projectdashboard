package com.api.dashboardproject.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.dashboardproject.entities.ProjectEntity;


public interface ProjectInterface {
	ProjectEntity saveProject(ProjectEntity entity);
	Page<ProjectEntity> getAllProjects(Pageable pageable);
	ProjectEntity getProjectById(String id);
	void removeProjectById(String id);
}
