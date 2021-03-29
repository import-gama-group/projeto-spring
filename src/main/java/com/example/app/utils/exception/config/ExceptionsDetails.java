package com.example.app.utils.exception.config;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "timestamp", "path", "status", "message"})
public class ExceptionsDetails {
	
	@JsonProperty("timestamp")
	private Instant timestamp;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("status")
	private HttpStatus status;
	
	@JsonProperty("path")
	private String path;
	
	
	
	public ExceptionsDetails() {
		super();
	
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus httpStatus) {
		this.status = httpStatus;
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
