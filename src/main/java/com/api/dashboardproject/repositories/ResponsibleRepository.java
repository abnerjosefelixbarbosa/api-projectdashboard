package com.api.dashboardproject.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.dashboardproject.entities.ResponsibleEntity;


@Repository
public interface ResponsibleRepository extends JpaRepository<ResponsibleEntity, String> {
	Page<ResponsibleEntity> findAll(Pageable pageable);
}
