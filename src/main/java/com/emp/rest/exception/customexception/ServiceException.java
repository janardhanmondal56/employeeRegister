package com.emp.rest.exception.customexception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 270420922960449480L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
