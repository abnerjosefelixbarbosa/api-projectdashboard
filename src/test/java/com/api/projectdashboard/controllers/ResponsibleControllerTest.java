package com.api.projectdashboard.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.api.projectdashboard.dtos.ResponsibleRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ResponsibleControllerTest {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;

	@Test
	//@Disabled
	public void saveResponsible() throws Exception {
		var dto = new ResponsibleRequestDto("name1", "user1", "123", "ADMIN");

		mvc.perform(post("/responsibles/save")
				.contentType("application/json")
				.content(mapper.writeValueAsString(dto)))
		.andDo(print())
		.andExpect(status().isCreated());
	}
}
