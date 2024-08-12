package com.example.demo.response;



public class ServiceResponse<T> {
	
	public T data;
			
	public boolean isSuccess;
	
	public String message;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceResponse(T data, boolean isSuccess, String message) {
		super();
		this.data = data;
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public ServiceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
