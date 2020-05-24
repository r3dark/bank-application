package org.example.bankApp.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends WrappedException {

	public InvalidRequestException(String message, HttpStatus code) {
		super(message, code);
	}
}
