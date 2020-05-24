package org.example.bankApp.exception;

import org.springframework.http.HttpStatus;

/**
 * @author rohitsharma
 */

public class InvalidRequestException extends WrappedException {

	public InvalidRequestException(String message, HttpStatus code) {
		super(message, code);
	}
}
