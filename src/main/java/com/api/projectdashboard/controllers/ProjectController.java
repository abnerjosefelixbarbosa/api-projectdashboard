package com.api.projectdashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.projectdashboard.dtos.ProjectRequestDto;
import com.api.projectdashboard.dtos.ProjetcResponseDto;
import com.api.projectdashboard.entities.ProjectEntity;
import com.api.projectdashboard.services.interfaces.ProjectServiceInterface;
import com.api.projectdashboard.services.interfaces.ResponsibleServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/projects")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectController {
	@Autowired
	private ProjectServiceInterface projectService;
	@Autowired
	private ResponsibleServiceInterface responsibleService;

	@PostMapping(path = "/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ProjetcResponseDto> saveProject(@RequestBody @Valid ProjectRequestDto dto) {
		var entity = new ProjectEntity(dto);
		var responsibleEntity = responsibleService.getResponsibleById(dto.getResponsibleId());
		entity.setResponsibleEntity(responsibleEntity);		
		return ResponseEntity.status(201).body(new ProjetcResponseDto(projectService.saveProject(entity)));
	}

	@PutMapping(path = "/edit/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<ProjetcResponseDto> editProject(@PathVariable String id,
			@RequestBody @Valid ProjectRequestDto dto) {
		var entity = new ProjectEntity(dto);		
		return ResponseEntity.status(200).body(new ProjetcResponseDto(projectService.editProject(id, entity)));
	}

	@GetMapping(path = "/get-all-by-responsible-id/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public ResponseEntity<Page<ProjetcResponseDto>> getAllProjectsByResponsibleId(@PathVariable String id, Pageable pageable) {
		var entities = projectService.getAllProjectsByResponsibleId(id, pageable);
		return ResponseEntity.status(200).body(entities.map(ProjetcResponseDto::new));
	} 

	@DeleteMapping(path = "/remove-by-id/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<ProjetcResponseDto> removeProjectById(@PathVariable String id) {
		projectService.removeProjectById(id);
		return ResponseEntity.status(204).body(null);
	}
}
