package com.emp.util.response;

public class SimpleResponse<T> {
	private T data;

	public SimpleResponse() {

	}

	public SimpleResponse(T data) {
		this.data=data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
