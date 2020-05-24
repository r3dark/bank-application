package org.example.bankApp.exception;

import org.springframework.http.HttpStatus;

/**
 * @author rohitsharma
 */

public class UnsuccessfulTransactionException extends WrappedException {

	public UnsuccessfulTransactionException(String message, HttpStatus code) {
		super(message, code);
	}
}
