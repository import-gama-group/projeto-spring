package com.example.app.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus
public class DefaultErrorException extends ResponseStatusException{
	
	private static final long serialVersionUID = 1L;

	public DefaultErrorException(HttpStatus status, String message) {
		super(status, message);
	}
	
}
