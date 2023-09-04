package com.api.dashboardproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;
import com.api.dashboardproject.dtos.ResponsibleResponseDto;
import com.api.dashboardproject.entities.ResponsibleEntity;
import com.api.dashboardproject.interfaces.ResponsibleServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/responsibles")
public class ResponsibleController {
	@Autowired
	private ResponsibleServiceInterface responsibleService;

	@PostMapping(path = "/save")
	public ResponseEntity<ResponsibleResponseDto> saveResponsible(@RequestBody @Valid ResponsibleRequestDto dto) {
		var entity = new ResponsibleEntity(dto);
		var newEntity = responsibleService.saveResponsible(entity);
		var response = new ResponsibleResponseDto(newEntity);
		return ResponseEntity.status(201).body(response);
	}

	@PutMapping(path = "/edit/{id}")
	public ResponseEntity<ResponsibleResponseDto> editResponsible(@PathVariable String id,
			@RequestBody @Valid ResponsibleRequestDto dto) {
		responsibleService.getResponsibleById(id);
		var entity = new ResponsibleEntity(dto);
		entity.setId(id);
		var newEntity = responsibleService.saveResponsible(entity);
		var response = new ResponsibleResponseDto(newEntity);
		return ResponseEntity.status(200).body(response);
	}

	@GetMapping(path = "/get-all")
	public ResponseEntity<Page<ResponsibleResponseDto>> getAllResponsible(Pageable pageable) {
		var responsibles = responsibleService.getAllResponsible(pageable);
		var response = responsibles.map(ResponsibleResponseDto::new);
		return ResponseEntity.status(200).body(response);
	}

	@GetMapping(path = "/get/{id}")
	public ResponseEntity<ResponsibleResponseDto> getResponsibleById(@PathVariable String id) {
		var entity = responsibleService.getResponsibleById(id);
		var response = new ResponsibleResponseDto(entity);
		return ResponseEntity.status(200).body(response);
	}

	@DeleteMapping(path = "/remove/{id}")
	public ResponseEntity<ResponsibleResponseDto> removeResponsibleById(@PathVariable String id) {
		responsibleService.getResponsibleById(id);
		responsibleService.removeResponsibleById(id);
		return ResponseEntity.status(204).body(null);
	}
}
