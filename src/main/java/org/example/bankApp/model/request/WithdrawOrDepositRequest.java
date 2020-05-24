package org.example.bankApp.model.request;

import org.springframework.stereotype.Component;

@Component
public class WithdrawOrDepositRequest {

	private Integer accountNumber;

	private Double amount;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "WithdrawOrDepositRequest{" +
				"accountNumber='" + accountNumber + '\'' +
				", amount=" + amount +
				'}';
	}
}
