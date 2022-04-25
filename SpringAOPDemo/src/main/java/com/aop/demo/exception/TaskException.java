package com.aop.demo.exception;

import org.springframework.http.HttpStatus;

public class TaskException extends RuntimeException {

	private HttpStatus statusCode;
	private String message;
	
	
	public TaskException(String message) {
		this.message = message;
	}
	public TaskException(HttpStatus statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
		public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
