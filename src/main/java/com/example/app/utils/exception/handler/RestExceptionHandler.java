package com.example.app.utils.exception.handler;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.app.utils.exception.BadRequesExceptionsDetails;
import com.example.app.utils.exception.BadRequestException;


@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BadRequesExceptionsDetails> handlerBadRequestExeception(BadRequestException bre, HttpServletRequest request){
		BadRequesExceptionsDetails err = new BadRequesExceptionsDetails();
				err.setTimestamp(Instant.now());
				err.setStatus(HttpStatus.BAD_REQUEST.value());
				err.setError("Controller not found");
				err.setMessage(bre.getMessage());
				err.setPath(request.getRequestURI());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
			
	}
	
	
}
