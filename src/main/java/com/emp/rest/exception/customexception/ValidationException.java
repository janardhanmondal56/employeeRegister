package com.emp.rest.exception.customexception;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class ValidationException extends ServiceException {

	/**
	 * the serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	private Errors errors;

	/**
	 * {@inheritDoc}
	 */
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	public ValidationException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}

	public ValidationException(Object target, String field, String code, String defaultMessage) {
		super("There are validation errors");
		this.errors = new BeanPropertyBindingResult(target, "data");
		this.errors.rejectValue(field, code, defaultMessage);
	}

	public Errors getErrors() {
		return errors;
	}

	@Override
	public void printStackTrace(PrintStream printStream) {
		super.printStackTrace(printStream);
		if (errors != null) {
			printStream.println("Validation errors:" + errors.toString());
		}
	}

	@Override
	public void printStackTrace(PrintWriter printStream) {
		super.printStackTrace(printStream);
		if (errors != null) {
			printStream.println("Validation errors:" + errors.toString());
		}
	}

}
