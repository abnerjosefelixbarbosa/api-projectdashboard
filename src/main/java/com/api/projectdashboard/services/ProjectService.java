package com.api.projectdashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.projectdashboard.entities.ProjectEntity;
import com.api.projectdashboard.exceptions.EntityBadRequestException;
import com.api.projectdashboard.interfaces.ProjectServiceInterface;
import com.api.projectdashboard.repositories.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService implements ProjectServiceInterface {
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectEntity saveProject(ProjectEntity entity) {
		validateProject(entity);
		return projectRepository.save(entity);
	}

	private void validateProject(ProjectEntity entity) {
		if (entity.getStartDate().isAfter(entity.getEndDate()) || entity.getStartDate().isEqual(entity.getEndDate()))
			throw new EntityBadRequestException("Date invalidate");
		if (entity.getBudget().scale() != 2)
			throw new EntityBadRequestException("Scale greater than 2");
	}

	public Page<ProjectEntity> getAllProjects(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}
	
	public Page<ProjectEntity> getAllProjectsByResponsibleId(String id, Pageable pageable) {
		return projectRepository.findAllByResponsibleEntityId(id, pageable);
	}

	public ProjectEntity getProjectById(String id) {
		return projectRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Project id not find");
		});
	}

	public void removeProjectById(String id) {
		projectRepository.deleteById(id);
	}
}
