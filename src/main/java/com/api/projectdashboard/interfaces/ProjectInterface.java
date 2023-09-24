package com.api.projectdashboard.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.projectdashboard.entities.ProjectEntity;


public interface ProjectInterface {
	ProjectEntity saveProject(ProjectEntity entity);
	Page<ProjectEntity> getAllProjects(Pageable pageable);
	Page<ProjectEntity> getAllProjectsByResponsibleId(String id, Pageable pageable);
	ProjectEntity getProjectById(String id);
	void removeProjectById(String id);
}
