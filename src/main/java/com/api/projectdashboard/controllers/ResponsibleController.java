package com.api.projectdashboard.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.projectdashboard.dtos.AuthenticationRequestDto;
import com.api.projectdashboard.dtos.AuthenticationResponseDto;
import com.api.projectdashboard.dtos.ResponsibleRequestDto;
import com.api.projectdashboard.dtos.ResponsibleResponseDto;
import com.api.projectdashboard.entities.ResponsibleEntity;
import com.api.projectdashboard.interfaces.ResponsibleServiceInterface;
import com.api.projectdashboard.interfaces.TokenServiceInterface;

import jakarta.servlet.http.HttpSession;
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
	public ResponseEntity<AuthenticationResponseDto> login(@RequestBody @Valid AuthenticationRequestDto dto, HttpSession session) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var responsible = (ResponsibleEntity) responsibleService.loadUserByUsername(dto.getLogin());
        
        var token = tokenService.generateToken((ResponsibleEntity) auth.getPrincipal());
        return ResponseEntity.status(200).body(new AuthenticationResponseDto(responsible, token));
	}

	@PostMapping(path = "/save")
	public ResponseEntity<ResponsibleResponseDto> saveResponsible(@RequestBody @Valid ResponsibleRequestDto dto) {
		var entity = new ResponsibleEntity(dto);
		return ResponseEntity.status(201).body(new ResponsibleResponseDto(responsibleService.saveResponsible(entity)));
	}

	@PutMapping(path = "/edit/{id}")
	public ResponseEntity<ResponsibleResponseDto> editResponsible(@PathVariable String id,
			@RequestBody @Valid ResponsibleRequestDto dto) {
		var entity = responsibleService.getResponsibleById(id);
		BeanUtils.copyProperties(dto, entity);
		return ResponseEntity.status(200).body(new ResponsibleResponseDto(responsibleService.saveResponsible(entity)));
	}

	@GetMapping(path = "/get-all")
	public ResponseEntity<Page<ResponsibleResponseDto>> getAllResponsibles(Pageable pageable) {
		var entities = responsibleService.getAllResponsible(pageable);
		return ResponseEntity.status(200).body(entities.map(ResponsibleResponseDto::new));
	}

	@GetMapping(path = "/get-by-id/{id}")
	public ResponseEntity<ResponsibleResponseDto> getResponsibleById(@PathVariable String id) {
		var entity = responsibleService.getResponsibleById(id);
		return ResponseEntity.status(200).body(new ResponsibleResponseDto(entity));
	}

	@DeleteMapping(path = "/remove-by-id/{id}")
	public ResponseEntity<ResponsibleResponseDto> removeResponsibleById(@PathVariable String id) {
		responsibleService.getResponsibleById(id);
		responsibleService.removeResponsibleById(id);
		return ResponseEntity.status(204).body(null);
	}
}
