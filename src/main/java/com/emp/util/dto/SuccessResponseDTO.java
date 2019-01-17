package com.emp.util.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SuccessResponseDTO implements Serializable {

	private static final long serialVersionUID = 1460065875784932377L;

	private boolean success;

	private Object resultObj;

	private String msg;

	public SuccessResponseDTO() {

	}

	public SuccessResponseDTO(boolean success, Object resultObj, String msg) {
		this.success = success;
		this.resultObj = resultObj;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "SuccessResponseDTO [success=" + success + ", resultObj=" + resultObj + ", msg=" + msg + "]";
	}
}
