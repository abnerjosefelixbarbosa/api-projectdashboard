package com.api.dashboardproject.interfaces;

import java.util.List;

import com.api.dashboardproject.dtos.ProjectRequestDto;
import com.api.dashboardproject.dtos.ProjetcResponseDto;


public interface ProjectInterface {
	ProjetcResponseDto saveProject(ProjectRequestDto dto);
	ProjetcResponseDto editProject(String id, ProjectRequestDto dto);
	List<ProjetcResponseDto> getAllProjects();
	ProjetcResponseDto getProjectById(String id);
	void removeProjectById(String id);
}
