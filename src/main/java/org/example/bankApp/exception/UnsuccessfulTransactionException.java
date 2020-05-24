package org.example.bankApp.exception;

import org.springframework.http.HttpStatus;

public class UnsuccessfulTransactionException extends WrappedException {

	public UnsuccessfulTransactionException(String message, HttpStatus code) {
		super(message, code);
	}
}
