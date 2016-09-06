package com.retail.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a Custom Validation exception class
 * @author Pulkit Garg
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1603908255810002890L;

	public ValidationException(String message) {
		super(message);
	}
}
