package com.api.dashboardproject.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequestDto(
		@NotEmpty(message = "login is empty") @NotNull(message = "login is null") String login,
		@NotEmpty(message = "password is empty") @NotNull(message = "password is null") String password) {

}
