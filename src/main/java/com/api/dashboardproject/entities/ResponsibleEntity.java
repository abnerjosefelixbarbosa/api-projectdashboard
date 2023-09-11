package com.api.dashboardproject.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "responsible_tb")
public class ResponsibleEntity implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	@Column(name = "login", nullable = false, length = 100)
	private String login;
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	private ResponsibleRole role;
	@OneToMany(mappedBy = "responsibleEntity")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ProjectEntity> projectEntities;
	
	public ResponsibleEntity() {
		super();
	}

	public ResponsibleEntity(ResponsibleRequestDto dto) {
		this.name = dto.name();
		this.login = dto.login();
		this.password = dto.password();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ResponsibleRole getRole() {
		return role;
	}

	public void setRole(ResponsibleRole role) {
		this.role = role;
	}

	public List<ProjectEntity> getProjectEntities() {
		return projectEntities;
	}

	public void setProjectEntities(List<ProjectEntity> projectEntities) {
		this.projectEntities = projectEntities;
	}

	@Override
	public String toString() {
		return "ResponsibleEntity [id=" + id + ", name=" + name + ", login=" + login + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponsibleEntity other = (ResponsibleEntity) obj;
		return Objects.equals(id, other.id);
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
		return false;
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
