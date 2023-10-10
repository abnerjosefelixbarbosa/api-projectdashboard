package com.api.projectdashboard.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleLoginAndPasswordRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
}
