package com.api.projectdashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.projectdashboard.entities.ProjectEntity;
import com.api.projectdashboard.exceptions.EntityBadRequestException;
import com.api.projectdashboard.interfaces.ProjectServiceInterface;
import com.api.projectdashboard.interfaces.ResponsibleServiceInterface;
import com.api.projectdashboard.repositories.ProjectRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjectService implements ProjectServiceInterface {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ResponsibleServiceInterface responsibleService;

	public ProjectEntity saveProject(ProjectEntity entity) {
		validateProject(entity);
		return projectRepository.save(entity);
	}
	
	public ProjectEntity editProject(String id, ProjectEntity entity) {
		validateProject(entity);
		
		var projectEntity = getProjectById(id);
		projectEntity.setName(entity.getName());
		projectEntity.setStartDate(entity.getStartDate());
		projectEntity.setEndDate(entity.getEndDate());
		projectEntity.setBudget(entity.getBudget());
		
		return projectRepository.save(projectEntity);
	}

	private void validateProject(ProjectEntity entity) {
		if (entity.getStartDate().isAfter(entity.getEndDate()) || entity.getStartDate().isEqual(entity.getEndDate()))
			throw new EntityBadRequestException("Date invalidate");
		if (entity.getBudget().scale() != 2)
			throw new EntityBadRequestException("Budget is different than 2 scales");
		if (entity.getBudget().longValue() == 0) 
			throw new EntityBadRequestException("Budget is 0");
	}
	
	public Page<ProjectEntity> getAllProjectsByResponsibleId(String id, Pageable pageable) {
		responsibleService.getResponsibleById(id);		
		return projectRepository.findAllByResponsibleEntityId(id, pageable);
	}

	public void removeProjectById(String id) {
		getProjectById(id);
		projectRepository.deleteById(id);
	}
	
	private ProjectEntity getProjectById(String id) {
		return projectRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Project id not find");
		});
	}
}
