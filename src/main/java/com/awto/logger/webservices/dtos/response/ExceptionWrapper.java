package com.awto.logger.webservices.dtos.response;

import org.springframework.http.HttpStatus;

import com.awto.logger.commons.exceptions.CustomBaseException;

public class ExceptionWrapper {

	public HttpStatus httpStatus;
	public String[] messages;

	public ExceptionWrapper(CustomBaseException e) {
		{

			this.httpStatus = e.getHttpStatus();
			this.messages = e.getMessages();
		}

	}

}
