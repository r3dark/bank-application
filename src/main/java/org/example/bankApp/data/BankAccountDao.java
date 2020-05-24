package org.example.bankApp.data;

import org.example.bankApp.model.BankAccount;
import org.example.bankApp.model.Transaction;
import org.example.bankApp.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class BankAccountDao {

	@Autowired
	BankAccountRepository bankAccountRepository;

	public List<Transaction> getTransactionHistory(Integer accountNumber) {

		List<Transaction> transactions = new ArrayList<>();
		bankAccountRepository.findAll().forEach(bankAccount -> {
			if (bankAccount.getAccountNumber() != null && bankAccount.getAccountNumber().equals(accountNumber)) {
				transactions.addAll(bankAccount.getTransactions().stream().collect(Collectors.toList()));
			}
		});
		return transactions;
	}

	public List<Transaction> getMiniStatement(Integer accountNumber) {

		List<Transaction> transactions = new ArrayList<>();
		bankAccountRepository.findAll().forEach(bankAccount -> {
			if (bankAccount.getAccountNumber() != null && bankAccount.getAccountNumber().equals(accountNumber)) {
				transactions.addAll(bankAccount.getTransactions().subList(Math.max(bankAccount.getTransactions().size() - 10, 0), bankAccount.getTransactions().size()));
			}
		});
		return transactions;
	}

	public Double getCurrentBalance(Integer accountNumber) {

		AtomicReference<Double> balance = new AtomicReference<>();
		bankAccountRepository.findAll().forEach(bankAccount -> {
			if (bankAccount.getAccountNumber() != null && bankAccount.getAccountNumber().equals(accountNumber)) {
				balance.set(bankAccount.getCurrentBalance());
			}
		});
		return balance.get();
	}

	public BankAccount getAccountByAccountNumber(Integer accountNumber) {

		AtomicReference<BankAccount> account = new AtomicReference<>();
		bankAccountRepository.findAll()
			.forEach(bankAccount -> {
				if (bankAccount.getAccountNumber() != null && bankAccount.getAccountNumber().equals(accountNumber)) {
					account.set(bankAccount);
				}
			});
		return account.get();
	}

	public BankAccount saveBankAccount(BankAccount bankAccount) {

		return bankAccountRepository.save(bankAccount);
	}
}
