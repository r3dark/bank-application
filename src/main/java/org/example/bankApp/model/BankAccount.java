package org.example.bankApp.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * @author rohitsharma
 */

@Entity
@Component
public class BankAccount {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer accountNumber;

	private Double minimumBalance;

	private Double currentBalance;

	private Double interestRate;

	@OneToMany (targetEntity = Transaction.class, cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	private AccountType accountType;

	public enum AccountType {
		CURRENT, SAVING
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(Double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "BankAccount{" +
				"accountNumber='" + accountNumber + '\'' +
				", minimumBalance=" + minimumBalance +
				", currentBalance=" + currentBalance +
				", interestRate=" + interestRate +
				", transactions=" + transactions +
				", accountType=" + accountType +
				'}';
	}
}
