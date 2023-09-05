package com.api.dashboardproject.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dashboardproject.dtos.ProjectRequestDto;
import com.api.dashboardproject.dtos.ProjetcResponseDto;
import com.api.dashboardproject.entities.ProjectEntity;
import com.api.dashboardproject.interfaces.ProjectServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/projects")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectController {
	@Autowired
	private ProjectServiceInterface projectService;
	
	@PostMapping(path = "/save")
	public ResponseEntity<ProjetcResponseDto> saveProject(@RequestBody @Valid ProjectRequestDto dto) {
		var entity = new ProjectEntity(dto);	
		return ResponseEntity.status(201).body(new ProjetcResponseDto(projectService.saveProject(entity)));
	}
	
	@PutMapping(path = "/edit/{id}")
	public ResponseEntity<ProjetcResponseDto> editProject(@PathVariable String id, @RequestBody @Valid ProjectRequestDto dto) {
		var entity = projectService.getProjectById(id);	
		BeanUtils.copyProperties(dto, entity);
		return ResponseEntity.status(200).body(new ProjetcResponseDto(projectService.saveProject(entity)));
	}
	
	@GetMapping(path = "/get-all")
	public ResponseEntity<Page<ProjetcResponseDto>> getAllProjects(Pageable pageable) {
		var entities = projectService.getAllProjects(pageable);
		return ResponseEntity.status(200).body(entities.map(ProjetcResponseDto::new));
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<ProjetcResponseDto> getProjectById(@PathVariable String id) {
		var entity = projectService.getProjectById(id);	
		return ResponseEntity.status(200).body(new ProjetcResponseDto(entity));
	}
	
	@DeleteMapping(path = "/remover/{id}")
	public ResponseEntity<ProjetcResponseDto> removerProjectById(@PathVariable String id) {
		projectService.getProjectById(id);
		projectService.removeProjectById(id);
		return ResponseEntity.status(204).body(null);
	}
}
