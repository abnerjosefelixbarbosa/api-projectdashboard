package com.api.projectdashboard.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.projectdashboard.dtos.ResponsibleEditRequestDto;
import com.api.projectdashboard.dtos.ResponsiblePasswordRequestDto;
import com.api.projectdashboard.dtos.ResponsibleSaveRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String password;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity roleEntity;
	@OneToMany(mappedBy = "responsibleEntity")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ProjectEntity> projectEntities;

	public ResponsibleEntity(ResponsibleSaveRequestDto dto) {
		this.roleEntity = new RoleEntity();
		this.name = dto.getName();
		this.email = dto.getEmail();
		this.password = dto.getPassword();
		this.roleEntity.setName(dto.getRole());
	}
	
	public ResponsibleEntity(ResponsiblePasswordRequestDto dto) {
		this.roleEntity = new RoleEntity();
		this.password = dto.getPassword();
	}
	
	public ResponsibleEntity(ResponsibleEditRequestDto dto) {
		this.roleEntity = new RoleEntity();
		this.name = dto.getName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.roleEntity.getName() == "ADMIN")
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
		else
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return email;
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
