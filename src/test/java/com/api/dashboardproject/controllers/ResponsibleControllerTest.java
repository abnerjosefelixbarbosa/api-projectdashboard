package com.api.dashboardproject.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.api.dashboardproject.dtos.ResponsibleRequestDto;
import com.api.dashboardproject.dtos.ResponsibleResponseDto;
import com.api.dashboardproject.exceptions.EntityBadRequestException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ResponsibleControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	//@Disabled
	public void saveResponsible() {
		var dto = new ResponsibleRequestDto("name","user1", "123", "ADMIN");
		restTemplate.postForObject("/responsibles/save", dto, ResponsibleResponseDto.class);
        
		var exception = assertThrows(EntityBadRequestException.class, () -> {
			Integer.parseInt("1a");
		});
		
		var expectedMessage = "Login or password exists";
	    var actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}
}
