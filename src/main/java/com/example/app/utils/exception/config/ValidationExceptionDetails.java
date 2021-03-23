package com.example.app.utils.exception.config;

public class ValidationExceptionDetails extends ExceptionsDetails {
	
	private  String fields;
	private  String fieldsMessage;
	
	
	public ValidationExceptionDetails() {
		super();
		
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getFieldsMessage() {
		return fieldsMessage;
	}
	public void setFieldsMessage(String fieldsMessage) {
		this.fieldsMessage = fieldsMessage;
	}
	
	
	
	
	
	

}