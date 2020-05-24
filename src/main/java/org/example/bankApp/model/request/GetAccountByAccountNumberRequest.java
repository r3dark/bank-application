package org.example.bankApp.model.request;

import org.springframework.stereotype.Component;

@Component
public class GetAccountByAccountNumberRequest {

	private Integer accountNumber;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "GetAccountByAccountNumberRequest{" +
				"accountNumber='" + accountNumber + '\'' +
				'}';
	}
}
