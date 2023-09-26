package com.api.projectdashboard.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.projectdashboard.entities.ResponsibleEntity;

public interface ResponsibleInterface {
	ResponsibleEntity saveResponsible(ResponsibleEntity entity);
	Page<ResponsibleEntity> getAllResponsible(Pageable pageable);
	ResponsibleEntity getResponsibleById(String id);
	void removeResponsibleById(String id);
}