package com.api.dashboardproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.dashboardproject.entities.ResponsibleEntity;
import com.api.dashboardproject.exceptions.EntityBadRequestException;
import com.api.dashboardproject.interfaces.ResponsibleServiceInterface;
import com.api.dashboardproject.repositories.ResponsibleRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ResponsibleService implements ResponsibleServiceInterface {
	@Autowired
	private ResponsibleRepository responsibleRepository;

	@Transactional
	public ResponsibleEntity saveResponsible(ResponsibleEntity entity) {
		validateEncoder(entity.getLogin(), entity.getPassword());
		var encoder = crypt().encode(entity.getPassword());
		entity.setPassword(encoder);
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

	public UserDetails loadUserByUsername(String login) {
		return responsibleRepository.findByLogin(login).orElseThrow(() -> {
			throw new UsernameNotFoundException("Login not found");
		});
	}

	private void validateEncoder(String login, String password) {
		var stream = responsibleRepository.findAll().stream();
		var matches = stream.anyMatch((val) -> crypt().matches(password, val.getPassword()) || login.equals(val.getLogin()));
		
		if (matches)
			throw new EntityBadRequestException("Login or password exists");
	}

	private BCryptPasswordEncoder crypt() {
		return new BCryptPasswordEncoder();
	}
}
