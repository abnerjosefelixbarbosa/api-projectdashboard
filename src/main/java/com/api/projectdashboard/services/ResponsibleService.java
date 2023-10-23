package com.api.projectdashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.projectdashboard.controllers.EntityBadRequestException;
import com.api.projectdashboard.entities.ResponsibleEntity;
import com.api.projectdashboard.entities.RoleEntity;
import com.api.projectdashboard.repositories.ResponsibleRepository;
import com.api.projectdashboard.repositories.RoleRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ResponsibleService implements ResponsibleServiceInterface {
	@Autowired
	private ResponsibleRepository responsibleRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public ResponsibleEntity saveResponsible(ResponsibleEntity entity) {
		validateEncoder(entity.getEmail(), entity.getPassword());
		var encoder = crypt().encode(entity.getPassword());
		entity.setPassword(encoder);
		var roleEntity = getRoleByName(entity.getRoleEntity().getName());
		entity.setRoleEntity(roleEntity);
		return responsibleRepository.save(entity);
	}

	@Transactional
	public ResponsibleEntity editResponsible(String id, ResponsibleEntity entity) {
		var responsibleEntityFound = getResponsibleById(id);
		responsibleEntityFound.setName(entity.getName());
		return responsibleRepository.save(responsibleEntityFound);
	}

	@Transactional
	public ResponsibleEntity editResponsiblePassword(String id, ResponsibleEntity entity) {
		validatePassword(entity.getPassword());
		var responsibleEntityFound = getResponsibleById(id);
		var encoder = crypt().encode(entity.getPassword());
		responsibleEntityFound.setPassword(encoder);
		return responsibleRepository.save(responsibleEntityFound);
	}

	public ResponsibleEntity getResponsibleById(String id) {
		return responsibleRepository.findById(id).orElseThrow(() -> {
			throw new EntityNotFoundException("Responsible id not find");
		});
	}

	public void removeResponsibleById(String id) {
		responsibleRepository.deleteById(id);
	}

	public UserDetails loadUserByUsername(String email) {
		return responsibleRepository.findByEmail(email).orElseThrow(() -> {
			throw new UsernameNotFoundException("Email not found");
		});
	}

	private BCryptPasswordEncoder crypt() {
		return new BCryptPasswordEncoder();
	}
	
	private void validatePassword(String password) {
		var stream = responsibleRepository.findAll().stream();		
		var matches = stream
				.anyMatch((val) -> crypt().matches(password, val.getPassword()));
		if (matches)
			throw new EntityBadRequestException("Password exists");
	}

	private void validateEncoder(String email, String password) {
		var stream = responsibleRepository.findAll().stream();
		var matches = stream
				.anyMatch((val) -> crypt().matches(password, val.getPassword()) || email.equals(val.getEmail()));
		if (matches)
			throw new EntityBadRequestException("Email or password exists");
	}
	
	private RoleEntity getRoleByName(String name) {
		var roleEntity = roleRepository.findByName(name).orElseThrow(() -> {
			throw new EntityNotFoundException("Role name not found");
		});
		return roleEntity;
	}
}
