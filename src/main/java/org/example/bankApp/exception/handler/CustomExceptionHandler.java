package org.example.bankApp.exception.handler;

import org.example.bankApp.exception.InvalidRequestException;
import org.example.bankApp.exception.UnsuccessfulTransactionException;
import org.example.bankApp.exception.WrappedException;
import org.example.bankApp.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author rohitsharma
 */

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
		return new ResponseEntity<>(getErrorResponseObject(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnsuccessfulTransactionException.class)
	public ResponseEntity<ErrorResponse> handleUnsuccessfulTransactionException(UnsuccessfulTransactionException ex) {
		return new ResponseEntity<>(getErrorResponseObject(ex), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(WrappedException.class)
	public ResponseEntity<ErrorResponse> handleWrappedException(WrappedException ex) {
		return new ResponseEntity<>(getErrorResponseObject(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse getErrorResponseObject(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(ex.getMessage());
		return errorResponse;
	}
}
