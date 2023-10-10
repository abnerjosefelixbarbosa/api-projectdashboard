package com.api.projectdashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.projectdashboard.entities.ResponsibleEntity;
import com.api.projectdashboard.entities.RoleEntity;
import com.api.projectdashboard.exceptions.EntityBadRequestException;
import com.api.projectdashboard.interfaces.ResponsibleServiceInterface;
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
	public ResponsibleEntity editResponsibleLoginAndPassword(String id, ResponsibleEntity entity) {
		validateEncoder(entity.getEmail(), entity.getPassword());

		var responsibleEntityFound = getResponsibleById(id);
		responsibleEntityFound.setEmail(entity.getEmail());
		var encoder = crypt().encode(entity.getPassword());
		responsibleEntityFound.setPassword(encoder);

		return responsibleRepository.save(responsibleEntityFound);
	}

	private void validateEncoder(String login, String password) {
		var stream = responsibleRepository.findAll().stream();
		var matches = stream
				.anyMatch((val) -> crypt().matches(password, val.getPassword()) || login.equals(val.getEmail()));

		if (matches)
			throw new EntityBadRequestException("Login or password exists");
	}

	private RoleEntity getRoleByName(String name) {
		var roleEntity = roleRepository.findByName(name).orElseThrow(() -> {
			throw new EntityNotFoundException("Role name not found");
		});

		return roleEntity;
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

	private BCryptPasswordEncoder crypt() {
		return new BCryptPasswordEncoder();
	}
}
