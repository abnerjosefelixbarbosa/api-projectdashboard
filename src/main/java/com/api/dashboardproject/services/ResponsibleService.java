package com.api.dashboardproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.dashboardproject.entities.ResponsibleEntity;
import com.api.dashboardproject.interfaces.ResponsibleServiceInterface;
import com.api.dashboardproject.repositories.ResponsibleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ResponsibleService implements ResponsibleServiceInterface {
	@Autowired
	private ResponsibleRepository responsibleRepository;

	public ResponsibleEntity saveResponsible(ResponsibleEntity entity) {
		return responsibleRepository.save(entity);
	}

	public Page<ResponsibleEntity> getAllResponsible(Pageable pageable) {
		return responsibleRepository.findAll(pageable);
	}

	public ResponsibleEntity getResponsibleById(String id) {
		return responsibleRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Responsible id not find");
		});
	}

	public void removeResponsibleById(String id) {
		responsibleRepository.deleteById(id);
	}
}
