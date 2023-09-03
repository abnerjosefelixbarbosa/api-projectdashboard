package com.api.dashboardproject.exceptions;

import lombok.Data;

@Data
public class HandleError {
	private String timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
