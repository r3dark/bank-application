package org.example.bankApp.model.response;

/**
 * @author rohitsharma
 */

public class ErrorResponse {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorResponse{" +
				"errorMessage='" + errorMessage + '\'' +
				'}';
	}
}
