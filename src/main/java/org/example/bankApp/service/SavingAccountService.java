package org.example.bankApp.service;

import org.example.bankApp.model.BankAccount;
import org.springframework.stereotype.Service;

/**
 * @author rohitsharma
 */

@Service
public class SavingAccountService extends BankAccountService {

	private static final Double MINIMUM_BALANCE = 10000.0;

	private static final Double INTEREST_RATE = 4.5;

	@Override
	public String getType() {
		return BankAccount.AccountType.SAVING.name();
	}

	@Override
	public Double getMinimumBalance() {
		return MINIMUM_BALANCE;
	}

	@Override
	public Double getInterestRate() {
		return INTEREST_RATE;
	}
}
