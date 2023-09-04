package com.api.dashboardproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.dashboardproject.entities.ResponsibleEntity;
import com.api.dashboardproject.exceptions.EntityNotFoundException;
import com.api.dashboardproject.interfaces.ResponsibleServiceInterface;
import com.api.dashboardproject.repositories.ResponsibleRepository;

@Service
public class ResponsibleService implements ResponsibleServiceInterface {
	@Autowired
	private ResponsibleRepository responsibleRepository;

	public ResponsibleEntity saveResponsible(ResponsibleEntity entity) {
		var response = responsibleRepository.save(entity);
		return response;
	}

	public Page<ResponsibleEntity> getAllResponsible(Pageable pageable) {
		var response = responsibleRepository.findAll(pageable);
		return response;
	}

	public ResponsibleEntity getResponsibleById(String id) {
		var response = responsibleRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Id not find");
		});
		return response;
	}

	public void removeResponsibleById(String id) {
		responsibleRepository.deleteById(id);
	}
}
