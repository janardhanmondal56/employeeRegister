package com.emp.rest.exception.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.emp.rest.exception.customexception.ValidationException;
import com.emp.util.response.ErrorResponse;

@ControllerAdvice
public class CommonRestExceptionsHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonRestExceptionsHandler.class);

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception) {
		LOGGER.error("ValidationException :", exception);
		ErrorResponse errorResponse = new ErrorResponse("Validation Error");
		errorResponse.setDetail(exception.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		if (exception.getErrors() != null) {
			List<FieldError> fieldErrors = new ArrayList<>();
			for (org.springframework.validation.FieldError error : exception.getErrors().getFieldErrors()) {
				fieldErrors.add(new FieldError(error.getField(), error.getDefaultMessage()));
			}
			errorResponse.setFieldErrors(fieldErrors);
		}
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SecurityException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleSecurityException(SecurityException exception) {
		LOGGER.error("SecurityException :", exception);
		ErrorResponse errorResponse = new ErrorResponse("Validation Error");
		errorResponse.setDetail("Access violation");
		errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException exception) {
		LOGGER.error("RuntimeException :", exception);
		ErrorResponse errorResponse = new ErrorResponse("Runtime Error");
		errorResponse.setDetail("Internal server error. Contact System Administrator");
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PropertyReferenceException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handlePropertyReferenceException(PropertyReferenceException propertyReferenceException) {
		LOGGER.error("PropertyReferenceException :", propertyReferenceException);
		ErrorResponse errorResponse = new ErrorResponse("Runtime Error");
		errorResponse.setDetail(propertyReferenceException.getMessage());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleTypeMismatchException(TypeMismatchException typeMismatchException) {
		ErrorResponse errorResponse = new ErrorResponse("Validation Error");
		errorResponse.setDetail("Invalid request parameters!");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		LOGGER.error("Exception :", typeMismatchException);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException httpMessageNotReadableException) {
		LOGGER.error("Exception :", httpMessageNotReadableException);
		ErrorResponse errorResponse = new ErrorResponse("Validation Error");
		errorResponse.setDetail("Invalid Data type in request body!!");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		LOGGER.error("Exception :", exception);
		ErrorResponse errorResponse = new ErrorResponse("Runtime Error");
		errorResponse.setDetail("Internal server error. Contact System Administrator");
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpServletRequest request,
			HttpRequestMethodNotSupportedException exception) {
		LOGGER.error("Exception :", exception);
		ErrorResponse errorResponse = new ErrorResponse("Invalid request method!");
		errorResponse.setDetail("Request Method '" + request.getMethod() + "' not supported!");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JpaSystemException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleJPAException(JpaSystemException jpaSystemException) {
		LOGGER.error("JpaSystemException :", jpaSystemException);
		if (jpaSystemException.getCause() instanceof PersistenceException
				&& jpaSystemException.getCause().getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException constraintViolationException = (ConstraintViolationException) jpaSystemException.getCause()
					.getCause();
			return this.constructErrorMessage(constraintViolationException.getConstraintName());
		}
		return new ResponseEntity<ErrorResponse>(this.getErrorResponse("Runtime Error",
				"Internal server error. Contact System Administrator", HttpStatus.INTERNAL_SERVER_ERROR),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(
			DataIntegrityViolationException dataIntegrityViolationException) {
		LOGGER.error("DataIntegrityViolationException :", dataIntegrityViolationException);
		if (dataIntegrityViolationException.getCause() instanceof ConstraintViolationException) {
			ConstraintViolationException constraintViolationException = (ConstraintViolationException) dataIntegrityViolationException
					.getCause();
			if (StringUtils.isNotBlank(constraintViolationException.getConstraintName())) {
				return this.constructErrorMessage(constraintViolationException.getConstraintName());
			}
		}
		return new ResponseEntity<ErrorResponse>(this.getErrorResponse("Runtime Error",
				"Internal server error. Contact System Administrator", HttpStatus.INTERNAL_SERVER_ERROR),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody	
	public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException missingServletRequestParameterException) {
		LOGGER.error("MissingServletRequestParameterException :", missingServletRequestParameterException);		
		ErrorResponse errorResponse = new ErrorResponse("Validation Error");
		errorResponse.setDetail(missingServletRequestParameterException.getLocalizedMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<ErrorResponse> constructErrorMessage(String constraintName) {
			return new ResponseEntity<ErrorResponse>(this.getErrorResponse("Runtime Error",
					"Internal server error. Contact System Administrator", HttpStatus.INTERNAL_SERVER_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse getErrorResponse(String errorMessage, String errorDetails, HttpStatus httpStatus) {
		ErrorResponse errorResponse = new ErrorResponse(errorMessage);
		errorResponse.setStatus(httpStatus.value());
		errorResponse.setDetail(errorDetails);
		return errorResponse;
	}
}