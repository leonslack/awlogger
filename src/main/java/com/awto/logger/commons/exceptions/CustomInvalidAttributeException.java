package com.awto.logger.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CustomInvalidAttributeException extends CustomBaseException {

	private static final long serialVersionUID = 6965229698022307627L;

	public CustomInvalidAttributeException(String message) {
		super(message);
		setHttpStatus(HttpStatus.BAD_REQUEST);
		
	}

}
