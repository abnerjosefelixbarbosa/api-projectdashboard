package com.api.projectdashboard.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.projectdashboard.entities.ProjectEntity;


public interface ProjectInterface {
	ProjectEntity saveProject(ProjectEntity entity);
	ProjectEntity editProject(String id, ProjectEntity entity);
	Page<ProjectEntity> getAllProjectsByResponsibleId(String id, Pageable pageable);
	void removeProjectById(String id);
}
