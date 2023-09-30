package com.api.projectdashboard.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.projectdashboard.entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity , String>  {
	Optional<RoleEntity> findByName(String name);
}
