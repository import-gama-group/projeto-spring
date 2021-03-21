package com.example.app.utils.exception;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

public class BadRequesExceptionsDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Integer status;
	private String error;
	private String path;
	private Instant timestamp;
	
	
	public BadRequesExceptionsDetails() {
		
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Instant getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	

	
}
