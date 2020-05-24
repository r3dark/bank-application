package org.example.bankApp.exception;

/**
 * @author rohitsharma
 */

public enum ErrorMessages {

	INVALID_BRANCH_ID ("Invalid request. Required field Branch Id can not be null, empty or less than 1"),

	UNABLE_TO_CREATE_BRANCH ("Internal server error. Could not create branch"),

	INVALID_PAN_NUMBER ("Invalid request. Required field PAN number can not be null or empty"),

	INVALID_ACCOUNT_NUMBER ("Invalid request. Required field account number can not be null or empty"),

	INSUFFICIENT_BALANCE ("Insufficient balance. Could not process the request as minimum balance has to be maintained. Minimum balance : "),

	INVALID_AMOUNT ("Invalid request. Amount entered should not be null or less than 1"),

	BRANCH_NOT_FOUND ("Requested branch not found"),

	ACCOUNT_NOT_FOUND ("Account does not exist with specified account number"),

	INTERNAL_SERVER_ERROR ("Internal Server Error Occurred."),

	INSUFFICIENT_AMOUNT ("Amount entered is less than required minimum balance for mentioned account type. Minimum balance : ");

	private final String message;

	ErrorMessages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
