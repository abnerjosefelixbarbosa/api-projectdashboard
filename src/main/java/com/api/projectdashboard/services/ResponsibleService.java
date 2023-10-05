package com.api.projectdashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.projectdashboard.entities.ResponsibleEntity;
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
		validateEncoder(entity.getLogin(), entity.getPassword());
		var encoder = crypt().encode(entity.getPassword());
		entity.setPassword(encoder);
		
		 var roleEntity = roleRepository.findByName(entity.getRoleEntity().getName()).orElseThrow(() -> {
			 throw new EntityNotFoundException("Role name not found");
		 });		 
		 entity.setRoleEntity(roleEntity);
		
		return responsibleRepository.save(entity);
	}
	
	@Transactional
	public ResponsibleEntity editResponsible(ResponsibleEntity entity) {
		validateEncoder(entity.getLogin(), entity.getPassword());
		var encoder = crypt().encode(entity.getPassword());
		entity.setPassword(encoder);
		
		 var roleEntity = roleRepository.findByName(entity.getRoleEntity().getName()).orElseThrow(() -> {
			 throw new EntityNotFoundException("Role name not found");
		 });		 
		 entity.setRoleEntity(roleEntity);
		
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
		//System.out.println(login);
		//System.out.println(password);
		var stream = responsibleRepository.findAll().stream();
		//var matches = stream.anyMatch((val) -> crypt().matches(password, val.getPassword()) || login.equals(val.getLogin()));
		
		stream.forEach((val) -> {
			//System.out.println(login.equals(val.getLogin()));
			System.out.println(crypt().matches(password, val.getPassword()));
		});
		
		//if (matches)
			//throw new EntityBadRequestException("Login or password exists");
	}

	private BCryptPasswordEncoder crypt() {
		return new BCryptPasswordEncoder();
	}
}
