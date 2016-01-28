package com.jsc.javawild.messanger.model;

public class ErrorMessage {
	private String errorMessage;
	private int statusCode;
	private String documentation;

	public ErrorMessage() {
	}

	public ErrorMessage(String errorMessage, int statusCode,
			String documentation) {
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
		this.documentation = documentation;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getDocumentation() {
		return documentation;
	}

	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

}
