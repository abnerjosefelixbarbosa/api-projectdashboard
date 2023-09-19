package com.api.dashboardproject.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "responsible_tb")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleEntity implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	@Column(name = "login", nullable = false, unique = true, length = 100)
	private String login;
	@Column(name = "password", nullable = false, unique = true, length = 100)
	private String password;
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private ResponsibleRole role;
	@OneToMany(mappedBy = "responsibleEntity")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ProjectEntity> projectEntities;

	public ResponsibleEntity(ResponsibleRequestDto dto) {
		this(null, dto.getName(), dto.getLogin(), new BCryptPasswordEncoder().encode(dto.getPassword()), ResponsibleRole.valueOf(dto.getRole()), null);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == ResponsibleRole.ADMIN)
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
