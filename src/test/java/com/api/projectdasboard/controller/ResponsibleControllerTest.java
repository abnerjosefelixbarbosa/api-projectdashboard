package com.api.projectdasboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.api.projectdashboard.ProjectDashboardApplication;
import com.api.projectdashboard.dtos.ResponsibleNameRequestDto;
import com.api.projectdashboard.dtos.ResponsibleRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = ProjectDashboardApplication.class)
@AutoConfigureMockMvc
public class ResponsibleControllerTest {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;

	@Test
	@Disabled
	public void saveResponsible() throws Exception {
		var dto = new ResponsibleRequestDto("name1", "login1", "password1", "ADMIN");
		
		mvc.perform(post("/responsibles/save")
				.contentType("application/json")
				.content(mapper.writeValueAsString(dto)))
		.andDo(print())
		.andExpect(status().is(201));
	}
	
	@Test
	public void editResponsibleName() throws Exception {
		var dto = new ResponsibleNameRequestDto("name2");
		
		mvc.perform(put("/responsibles/edit-name/dee1a11e-e1e6-4b51-9649-2404e74bdee6")
				.contentType("application/json")
				.content(mapper.writeValueAsString(dto)))
		.andDo(print())
		.andExpect(status().is(200));
	}
}