package com.karthik.rest.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorEntity {

	private int errorCode;
	private String errorMessage;
	
	public ErrorEntity() {}

	public ErrorEntity(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
