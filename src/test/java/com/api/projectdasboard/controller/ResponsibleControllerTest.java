package com.api.projectdasboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.api.projectdashboard.dtos.AuthenticationRequestDto;
import com.api.projectdashboard.dtos.ResponsibleEditRequestDto;
import com.api.projectdashboard.dtos.ResponsiblePasswordRequestDto;
import com.api.projectdashboard.dtos.ResponsibleSaveRequestDto;
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
	public void loginResponsible() throws Exception {
		var dto = new AuthenticationRequestDto("login2", "password2");

		mvc.perform(post("/responsibles/login").contentType("application/json").content(mapper.writeValueAsString(dto)))
				.andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void saveResponsible() throws Exception {
		var dto = new ResponsibleSaveRequestDto("name1", "login1", "password1", "ADMIN");

		mvc.perform(post("/responsibles/save").contentType("application/json").content(mapper.writeValueAsString(dto)))
				.andDo(print()).andExpect(status().is(201));
	}

	@Test
	@Disabled
	public void editResponsible() throws Exception {
		var dto = new ResponsibleEditRequestDto("name2");

		mvc.perform(put("/responsibles/edit/dee1a11e-e1e6-4b51-9649-2404e74bdee6").contentType("application/json")
				.content(mapper.writeValueAsString(dto))).andDo(print()).andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void editResponsibleLoignAndPassword() throws Exception {
		var dto = new ResponsiblePasswordRequestDto("password2");

		mvc.perform(put("/responsibles/edit-login-and-password/dee1a11e-e1e6-4b51-9649-2404e74bdee6")
				.contentType("application/json").content(mapper.writeValueAsString(dto))).andDo(print())
				.andExpect(status().is(200));
	}

	@Test
	@Disabled
	public void removeResponsibleById() throws Exception {
		mvc.perform(delete("/responsibles/remove-by-id/dee1a11e-e1e6-4b51-9649-2404e74bdee6")
				.contentType("application/json")).andDo(print()).andExpect(status().is(204));
	}
}