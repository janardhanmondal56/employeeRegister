package com.emp.util.response;

import java.util.ArrayList;
import java.util.List;

import com.emp.rest.exception.handler.FieldError;

public class ErrorResponse {

	private int status;

	private String title;

	private String detail;
	
	private List<FieldError> fieldErrors = new ArrayList<FieldError>();

	public ErrorResponse() {

	}

	public ErrorResponse(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
