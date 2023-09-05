package com.api.dashboardproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.dashboardproject.entities.ProjectEntity;
import com.api.dashboardproject.interfaces.ProjectServiceInterface;
import com.api.dashboardproject.repositories.ProjectRepository;
import com.api.dashboardproject.repositories.ResponsibleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService implements ProjectServiceInterface {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ResponsibleRepository responsibleRepository;

	public ProjectEntity saveProject(ProjectEntity entity) {
		var responsibleEntity = responsibleRepository.findById(entity.getResponsibleEntity().getId()).orElseThrow(() -> {
			throw new EntityNotFoundException("Responsible id not found");
		});
		entity.setResponsibleEntity(responsibleEntity);
		return projectRepository.save(entity);
	}

	public Page<ProjectEntity> getAllProjects(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}

	public ProjectEntity getProjectById(String id) {
		return projectRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
	}

	public void removeProjectById(String id) {
		projectRepository.deleteById(id);
	}
}
