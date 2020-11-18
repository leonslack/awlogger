package com.awto.logger.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CustomBaseException extends Exception {

	private static final long serialVersionUID = 4102859845129741744L;

	private HttpStatus httpStatus;

	protected String code;

	private String[] messages;

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String... messages) {
		this.messages = messages;
	}

	public CustomBaseException(String... message) {
		this.messages = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}
}
