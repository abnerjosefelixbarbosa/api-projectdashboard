package com.api.dashboardproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dashboardproject.dtos.ProjectRequestDto;
import com.api.dashboardproject.dtos.ProjetcResponseDto;
import com.api.dashboardproject.entities.ProjectEntity;
import com.api.dashboardproject.exceptions.EntityNotFoundException;
import com.api.dashboardproject.interfaces.ProjectServiceInterface;
import com.api.dashboardproject.repositories.ProjectRepository;
import com.api.dashboardproject.repositories.ResponsibleRepository;

@Service
public class ProjectService implements ProjectServiceInterface {
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ResponsibleRepository responsibleRepository;

	public ProjetcResponseDto saveProject(ProjectRequestDto dto) {
		var entity = new ProjectEntity(dto);
		var entityResponsible = responsibleRepository.findById(entity.getResponsibleEntity().getId()).orElseThrow(() -> {
			throw new EntityNotFoundException("Responsible id not find");
		});
		entity.setResponsibleEntity(entityResponsible);
 		var response = new ProjetcResponseDto(projectRepository.save(entity));
		return response;
	}

	public ProjetcResponseDto editProject(String id, ProjectRequestDto dto) {
		projectRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
		var entity = new ProjectEntity(dto);
		var entityResponsible = responsibleRepository.findById(entity.getId()).orElseThrow(() -> {
			throw new EntityNotFoundException("Responsible id not find");
		});
		entity.setResponsibleEntity(entityResponsible);
		var response = new ProjetcResponseDto(projectRepository.save(entity));
		return response;
	}

	public List<ProjetcResponseDto> getAllProjects() {
		var response = projectRepository.findAll().stream().map(ProjetcResponseDto::new).toList(); 
		return response;
	}

	public ProjetcResponseDto getProjectById(String id) {
		var entity = projectRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
		var response = new ProjetcResponseDto(entity);
		return response;
	}


	public void removeProjectById(String id) {
		responsibleRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
		responsibleRepository.deleteById(id);
	}
}
