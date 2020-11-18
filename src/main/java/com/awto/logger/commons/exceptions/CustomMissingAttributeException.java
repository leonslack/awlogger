package com.awto.logger.commons.exceptions;

import org.springframework.http.HttpStatus;

public class CustomMissingAttributeException extends CustomBaseException {

	private static final long serialVersionUID = 6965229698022307627L;

	public CustomMissingAttributeException(String message) {
		super(message);
		setHttpStatus(HttpStatus.BAD_REQUEST);
		
	}

}
