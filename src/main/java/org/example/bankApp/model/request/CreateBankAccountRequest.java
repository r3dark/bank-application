package org.example.bankApp.model.request;

import org.example.bankApp.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class CreateBankAccountRequest {

	private String panNumber;

	private BankAccount.AccountType type;

	private Double amount;

	private Integer branchId;

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public BankAccount.AccountType getType() {
		return type;
	}

	public void setType(BankAccount.AccountType type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	@Override
	public String toString() {
		return "CreateBankAccountRequest{" +
				"panNumber='" + panNumber + '\'' +
				", type=" + type +
				", amount=" + amount +
				", branchId='" + branchId + '\'' +
				'}';
	}
}
