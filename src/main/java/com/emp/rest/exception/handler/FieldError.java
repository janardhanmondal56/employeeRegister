package com.emp.rest.exception.handler;

public class FieldError {

	private String fieldId;

	private String errorMessage;

	private Object data;

	public FieldError(String fieldId, String errorMessage) {
		setFieldId(fieldId);
		setErrorMessage(errorMessage);
	}

	public FieldError(String fieldId, String errorMessage, Object data) {
		setFieldId(fieldId);
		setErrorMessage(errorMessage);
		setData(data);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}