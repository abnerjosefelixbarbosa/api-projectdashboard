package com.api.projectdashboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.projectdashboard.dtos.AuthenticationRequestDto;
import com.api.projectdashboard.dtos.AuthenticationResponseDto;
import com.api.projectdashboard.dtos.ResponsibleEditRequestDto;
import com.api.projectdashboard.dtos.ResponsiblePasswordRequestDto;
import com.api.projectdashboard.dtos.ResponsibleResponseDto;
import com.api.projectdashboard.dtos.ResponsibleSaveRequestDto;
import com.api.projectdashboard.entities.ResponsibleEntity;
import com.api.projectdashboard.services.interfaces.ResponsibleServiceInterface;
import com.api.projectdashboard.services.interfaces.TokenServiceInterface;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/responsibles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResponsibleController {
	@Autowired
	private ResponsibleServiceInterface responsibleService;
	@Autowired
    private TokenServiceInterface tokenService;
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@PostMapping(path = "/login")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<AuthenticationResponseDto> loginResponsible(@RequestBody @Valid AuthenticationRequestDto dto) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var responsible = (ResponsibleEntity) responsibleService.loadUserByUsername(dto.getEmail());        
        var token = tokenService.generateToken((ResponsibleEntity) auth.getPrincipal());
        return ResponseEntity.status(200).body(new AuthenticationResponseDto(responsible, token));
	}

	@PostMapping(path = "/save")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ResponsibleResponseDto> saveResponsible(@RequestBody @Valid ResponsibleSaveRequestDto dto) {
		var entity = new ResponsibleEntity(dto);
		return ResponseEntity.status(201).body(new ResponsibleResponseDto(responsibleService.saveResponsible(entity)));
	}
	
	@PutMapping(path = "/edit/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponsibleResponseDto> editResponsible(@PathVariable String id,
			@RequestBody @Valid ResponsibleEditRequestDto dto) {
		var entity = new ResponsibleEntity(dto);
		return ResponseEntity.status(200).body(new ResponsibleResponseDto(responsibleService.editResponsible(id, entity)));
	}
	
	@PutMapping(path = "/edit-password/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponsibleResponseDto> editResponsiblePassword(@PathVariable String id,
			@RequestBody @Valid ResponsiblePasswordRequestDto dto) {
		var entity = new ResponsibleEntity(dto);
		return ResponseEntity.status(200).body(new ResponsibleResponseDto(responsibleService.editResponsiblePassword(id, entity)));
	}

	@DeleteMapping(path = "/remove-by-id/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<ResponsibleResponseDto> removeResponsibleById(@PathVariable String id) {
		responsibleService.getResponsibleById(id);
		responsibleService.removeResponsibleById(id);
		return ResponseEntity.status(204).body(null);
	}
}
