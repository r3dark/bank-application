package org.example.bankApp.processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bankApp.data.BankAccountDao;
import org.example.bankApp.exception.ErrorMessages;
import org.example.bankApp.exception.InvalidRequestException;
import org.example.bankApp.model.BankAccount;
import org.example.bankApp.model.Transaction;
import org.example.bankApp.model.request.TransactionsOrMiniStatementRequest;
import org.example.bankApp.model.request.WithdrawOrDepositRequest;
import org.example.bankApp.service.CurrentAccountService;
import org.example.bankApp.service.SavingAccountService;
import org.example.bankApp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AccountProcessor {

	@Autowired
	BankAccountDao bankAccountDao;

	@Autowired
	CurrentAccountService currentAccountService;

	@Autowired
	SavingAccountService savingAccountService;

	private static final String STATEMENT_TYPE_TRANSACTION_HISTORY = "transactionHistory";

	private static final Logger log = LogManager.getLogger(AccountProcessor.class);

	public String accountTypeProcessor(String requestBody, String transactionType) throws Exception {

		try {
			WithdrawOrDepositRequest withdrawOrDepositRequest = (WithdrawOrDepositRequest) Utils.toObject(requestBody, WithdrawOrDepositRequest.class);
			if (withdrawOrDepositRequest.getAccountNumber() != null) {
				BankAccount bankAccount = bankAccountDao.getAccountByAccountNumber(withdrawOrDepositRequest.getAccountNumber());

				if (bankAccount != null) {
					BankAccount.AccountType accountType = bankAccount.getAccountType();

					if (accountType.equals(BankAccount.AccountType.CURRENT)) {

						if (transactionType.equalsIgnoreCase(Transaction.TransactionType.WITHDRAW.name())) {
							return currentAccountService.withdraw(withdrawOrDepositRequest.getAmount(), bankAccount);
						} else {
							return currentAccountService.deposit(withdrawOrDepositRequest.getAmount(), bankAccount);
						}
					} else {

						if (transactionType.equalsIgnoreCase(Transaction.TransactionType.WITHDRAW.name())) {
							return savingAccountService.withdraw(withdrawOrDepositRequest.getAmount(), bankAccount);
						} else {
							return savingAccountService.deposit(withdrawOrDepositRequest.getAmount(), bankAccount);
						}
					}
				} else {
					throw new InvalidRequestException(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
				}
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_ACCOUNT_NUMBER.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while processing transaction request", ex);
			throw ex;
		}
	}

	public String accountStatementProcessor(String requestBody, String statementType) throws Exception {

		try {
			TransactionsOrMiniStatementRequest transactionsOrMiniStatementRequest = (TransactionsOrMiniStatementRequest) Utils.toObject(requestBody, TransactionsOrMiniStatementRequest.class);

			if (transactionsOrMiniStatementRequest.getAccountNumber() != null) {
				BankAccount bankAccount = bankAccountDao.getAccountByAccountNumber(transactionsOrMiniStatementRequest.getAccountNumber());

				if (bankAccount != null) {
					BankAccount.AccountType accountType = bankAccount.getAccountType();
					if (accountType.equals(BankAccount.AccountType.CURRENT)) {

						if (statementType.equalsIgnoreCase(STATEMENT_TYPE_TRANSACTION_HISTORY)) {
							return currentAccountService.getTransactionHistory(transactionsOrMiniStatementRequest.getAccountNumber());
						} else {
							return currentAccountService.getMiniStatement(transactionsOrMiniStatementRequest.getAccountNumber());
						}
					} else {

						if (statementType.equalsIgnoreCase(STATEMENT_TYPE_TRANSACTION_HISTORY)) {
							return savingAccountService.getTransactionHistory(transactionsOrMiniStatementRequest.getAccountNumber());
						} else {
							return savingAccountService.getMiniStatement(transactionsOrMiniStatementRequest.getAccountNumber());
						}
					}
				} else {
					throw new InvalidRequestException(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
				}
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_ACCOUNT_NUMBER.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while processing transaction request", ex);
			throw ex;
		}
	}
}
