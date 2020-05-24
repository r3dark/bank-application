package org.example.bankApp.model.request;

import org.springframework.stereotype.Component;

/**
 * @author rohitsharma
 */

@Component
public class TransactionsOrMiniStatementRequest {

	private Integer accountNumber;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "TransactionsOrMiniStatementRequest{" +
				"accountNumber='" + accountNumber + '\'' +
				'}';
	}
}
