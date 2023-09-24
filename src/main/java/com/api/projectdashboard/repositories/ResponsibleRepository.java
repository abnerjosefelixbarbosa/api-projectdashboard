package com.api.projectdashboard.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.api.projectdashboard.entities.ResponsibleEntity;


@Repository
public interface ResponsibleRepository extends JpaRepository<ResponsibleEntity, String> {
	Optional<UserDetails> findByLogin(String login);
}
