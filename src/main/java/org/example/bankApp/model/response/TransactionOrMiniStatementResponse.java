package org.example.bankApp.model.response;

import org.example.bankApp.model.Transaction;

import java.util.List;

/**
 * @author rohitsharma
 */

public class TransactionOrMiniStatementResponse {

	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "TransactionOrMiniStatementResponse{" +
				"transactions=" + transactions +
				'}';
	}
}
