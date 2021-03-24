package com.example.app.utils.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class DefaultErrorException extends RuntimeException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultErrorException(String message) {
		super(message);
	}
	
}
