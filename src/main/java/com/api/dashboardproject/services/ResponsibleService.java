package com.api.dashboardproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;
import com.api.dashboardproject.dtos.ResponsibleResponseDto;
import com.api.dashboardproject.entities.ResponsibleEntity;
import com.api.dashboardproject.exceptions.EntityNotFoundException;
import com.api.dashboardproject.interfaces.ResponsibleServiceInterface;
import com.api.dashboardproject.repositories.ResponsibleRepository;

@Service
public class ResponsibleService implements ResponsibleServiceInterface {
	@Autowired
	private ResponsibleRepository responsibleRepository;
	
	public ResponsibleResponseDto saveResponsible(ResponsibleRequestDto dto) {
		var newEntity = new ResponsibleEntity(dto);
		var response = new ResponsibleResponseDto(responsibleRepository.save(newEntity));
		return response;
	}
	
	public ResponsibleResponseDto editResponsible(String id, ResponsibleRequestDto dto) {
		responsibleRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
		var newEntity = new ResponsibleEntity(dto);
		newEntity.setId(id);
		var response = new ResponsibleResponseDto(responsibleRepository.save(newEntity));
		return response;
	}

	public List<ResponsibleResponseDto> getAllResponsible() {
		var response = responsibleRepository.findAll().stream().map(ResponsibleResponseDto::new).toList();
		return response;
	}

	public ResponsibleResponseDto getResponsibleById(String id) {
		var entity  = responsibleRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
		var response = new ResponsibleResponseDto(entity);
		return response;
	}

	public void removeResponsibleById(String id) {
		responsibleRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
		responsibleRepository.deleteById(id);
	}
}
