package com.api.dashboardproject.interfaces;

import java.util.List;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;
import com.api.dashboardproject.dtos.ResponsibleResponseDto;

public interface ResponsibleInterface {
	ResponsibleResponseDto saveResponsible(ResponsibleRequestDto dto);
	ResponsibleResponseDto editResponsible(String id, ResponsibleRequestDto dto);
	List<ResponsibleResponseDto> getAllResponsible();
	ResponsibleResponseDto getResponsibleById(String id);
	void removeResponsibleById(String id);
}
