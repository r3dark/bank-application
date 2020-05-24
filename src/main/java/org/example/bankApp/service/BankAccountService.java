package org.example.bankApp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bankApp.data.BankAccountDao;
import org.example.bankApp.exception.ErrorMessages;
import org.example.bankApp.exception.InvalidRequestException;
import org.example.bankApp.exception.UnsuccessfulTransactionException;
import org.example.bankApp.model.BankAccount;
import org.example.bankApp.model.Transaction;
import org.example.bankApp.model.response.TransactionOrMiniStatementResponse;
import org.example.bankApp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author rohitsharma
 */

@Service
public abstract class BankAccountService {

	@Autowired
	BankAccountDao bankAccountDao;

	private static final Logger log = LogManager.getLogger(BankAccountService.class);

	public String withdraw(Double amount, BankAccount bankAccount) throws Exception {

		try {
			if (amount != null && amount > 0.0) {
				if ((bankAccount.getCurrentBalance() - amount) >= getMinimumBalance()) {
					Transaction transaction = processTransaction(amount, bankAccount, Transaction.TransactionType.WITHDRAW);
					return Utils.toJson(transaction);
				} else {
					throw new UnsuccessfulTransactionException(ErrorMessages.INSUFFICIENT_BALANCE.getMessage() + getMinimumBalance()
							+ " and current balance after withdrawal : " + (bankAccount.getCurrentBalance() - amount), HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_AMOUNT.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error while withdrawing money from bank account : " + bankAccount.getAccountNumber(), ex);
			throw ex;
		}
	}

	public String deposit(Double amount, BankAccount bankAccount) throws Exception {

		try {
			if (amount != null && amount > 0.0) {
				Transaction transaction = processTransaction(amount, bankAccount, Transaction.TransactionType.DEPOSIT);
				return Utils.toJson(transaction);
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_AMOUNT.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error while withdrawing money from bank account : " + bankAccount.getAccountNumber(), ex);
			throw ex;
		}
	}

	public String getTransactionHistory(Integer accountNumber) throws Exception {

		try {
			List<Transaction> transactions = bankAccountDao.getTransactionHistory(accountNumber);
			TransactionOrMiniStatementResponse transactionOrMiniStatementResponse = new TransactionOrMiniStatementResponse();
			transactionOrMiniStatementResponse.setTransactions(transactions);
			return Utils.toJson(transactionOrMiniStatementResponse);
		} catch (Exception ex) {
			log.error("Error occurred while getting transaction history for account number : " + accountNumber, ex);
			throw ex;
		}
	}

	public String getMiniStatement(Integer accountNumber) throws Exception {

		try {
			List<Transaction> miniStatement = bankAccountDao.getMiniStatement(accountNumber);
			TransactionOrMiniStatementResponse transactionOrMiniStatementResponse = new TransactionOrMiniStatementResponse();
			transactionOrMiniStatementResponse.setTransactions(miniStatement);
			return Utils.toJson(transactionOrMiniStatementResponse);
		} catch (Exception ex) {
			log.error("Error occurred while getting mini statement for account number : " + accountNumber, ex);
			throw ex;
		}
	}

	public Double getCurrentBalance(Integer accountNumber) throws Exception {

		try {
			if (!StringUtils.isEmpty(accountNumber)) {
				return bankAccountDao.getCurrentBalance(accountNumber);
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_ACCOUNT_NUMBER.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while getting current baalnce for account number : " + accountNumber, ex);
			throw ex;
		}
	}

	private Transaction processTransaction(Double amount, BankAccount bankAccount, Transaction.TransactionType transactionType) {

		if (transactionType.equals(Transaction.TransactionType.WITHDRAW)) {
			bankAccount.setCurrentBalance(bankAccount.getCurrentBalance() - amount);
		} else {
			bankAccount.setCurrentBalance(bankAccount.getCurrentBalance() + amount);
		}

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setType(transactionType);

		bankAccount.getTransactions().add(transaction);

		BankAccount updatedBankAccount = bankAccountDao.saveBankAccount(bankAccount);
		return updatedBankAccount.getTransactions().stream().reduce((first, second) -> second).orElse(null);
	}

	public abstract Double getMinimumBalance();

	public abstract Double getInterestRate();

	public abstract String getType();
}
