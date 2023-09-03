package com.api.dashboardproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.dashboardproject.interfaces.ResponsibleServiceInterface;

@RestController
@RequestMapping(path = "/responsibles")
public class ResponsibleController {
	@Autowired
	private ResponsibleServiceInterface responsibleService;

	@PostMapping(path = "/save")
	public ResponseEntity<ResponsibleResponseDto> saveResponsible(@RequestBody ResponsibleRequestDto dto) {
		dto.setId(null);
		var response = responsibleService.saveResponsible(dto);
		return ResponseEntity.status(201).body(response);
	}

	@PutMapping(path = "/edit/{id}")
	public ResponseEntity<ResponsibleResponseDto> editResponsible(@PathVariable String id,
			@RequestBody ResponsibleRequestDto dto) {
		var response = responsibleService.editResponsible(id, dto);
		return ResponseEntity.status(200).body(response);
	}

	@GetMapping(path = "/get-all")
	public ResponseEntity<List<ResponsibleResponseDto>> getAllResponsible() {
		var response = responsibleService.getAllResponsible();
		return ResponseEntity.status(200).body(response);
	}
	
	@GetMapping(path = "/get/{id}")
	public ResponseEntity<ResponsibleResponseDto> getResponsibleById(@PathVariable String id) {
		var response = responsibleService.getResponsibleById(id);
		return ResponseEntity.status(200).body(response);
	}
	
	@DeleteMapping(path = "/remove/{id}")
	public ResponseEntity<ResponsibleResponseDto> removeResponsibleById(@PathVariable String id) {
		responsibleService.removeResponsibleById(id);
		return ResponseEntity.status(204).body(null);
	}
}
