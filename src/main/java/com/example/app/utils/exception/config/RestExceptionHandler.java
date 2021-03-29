package com.example.app.utils.exception.config;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.app.utils.exception.DefaultErrorException;


@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(DefaultErrorException.class)
	public ResponseEntity<BadRequesExceptionsDetails> handlerBadRequestException(DefaultErrorException bre, HttpServletRequest request){
		BadRequesExceptionsDetails err = new BadRequesExceptionsDetails();
				err.setTimestamp(Instant.now());
				err.setStatus(bre.getStatus());
				//err.setError(bre.getMessage());
				err.setMessage(bre.getReason());
				err.setPath(request.getRequestURI());
				return ResponseEntity.status(bre.getStatus()).body(err);
			
	}
	
}
