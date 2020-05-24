package org.example.bankApp.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Component
public class Transaction {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer transactionId;

	private Double amount;

	private TransactionType type;

	public enum TransactionType {
		DEPOSIT, WITHDRAW
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transaction{" +
				"transactionId='" + transactionId + '\'' +
				", amount=" + amount +
				", type=" + type +
				'}';
	}
}
