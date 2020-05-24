package org.example.bankApp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bankApp.data.BranchDao;
import org.example.bankApp.exception.ErrorMessages;
import org.example.bankApp.exception.InvalidRequestException;
import org.example.bankApp.exception.WrappedException;
import org.example.bankApp.model.BankAccount;
import org.example.bankApp.model.Branch;
import org.example.bankApp.model.request.CreateBankAccountRequest;
import org.example.bankApp.model.Customer;
import org.example.bankApp.model.request.GetAccountByAccountNumberRequest;
import org.example.bankApp.model.request.GetCustomerByPanRequest;
import org.example.bankApp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rohitsharma
 */

@Service
public class BranchService {

	@Autowired
	BranchDao branchDao;

	private static final Logger log = LogManager.getLogger(BranchService.class);

	public String createBankAccount(String requestBody) throws Exception {

		try {
			CreateBankAccountRequest createBankAccountRequest = (CreateBankAccountRequest) Utils.toObject(requestBody, CreateBankAccountRequest.class);
			boolean isBranchPresent = false;

			Iterable<Branch> branchIterable = branchDao.getAllBranches();

			if (branchIterable.spliterator().getExactSizeIfKnown() == 0) {
				throw new WrappedException(ErrorMessages.BRANCH_NOT_FOUND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			Branch savedBranch = new Branch();
			createBankAccountLoop:
			for (Branch branch : branchIterable) {
				if (branch.getBranchId().equals(createBankAccountRequest.getBranchId())) {
					isBranchPresent = true;
					for (Customer customer : branch.getCustomers()) {
						if (customer.getPanNumber().equalsIgnoreCase(createBankAccountRequest.getPanNumber())) {
							BankAccount bankAccount = new BankAccount();
							populateBankAccountObject(bankAccount, createBankAccountRequest);

							customer.getAccounts().add(bankAccount);

							branch.getBankAccounts().add(bankAccount);

							savedBranch = updateBranchToDataBase(branch);
							break createBankAccountLoop;
						}
					}

					BankAccount bankAccount = new BankAccount();
					populateBankAccountObject(bankAccount, createBankAccountRequest);

					Customer customer = new Customer();
					populateCustomerObject(customer, bankAccount, createBankAccountRequest);

					branch.getCustomers().add(customer);
					branch.getBankAccounts().add(bankAccount);

					savedBranch = updateBranchToDataBase(branch);
					break;
				}
			}

			if (!isBranchPresent) {
				throw new InvalidRequestException(ErrorMessages.INVALID_BRANCH_ID.getMessage(), HttpStatus.BAD_REQUEST);
			}
			return Utils.toJson(savedBranch);
		} catch (Exception ex) {
			log.error("Exception occurred while creating bank account for request : " + requestBody);
			throw ex;
		}
	}

	public String getCustomerByPanNumber(String requestBody) throws Exception {

		try {
			GetCustomerByPanRequest getCustomerByPanRequest = (GetCustomerByPanRequest) Utils.toObject(requestBody, GetCustomerByPanRequest.class);
			if (!StringUtils.isEmpty(getCustomerByPanRequest.getPanNumber())) {
				Customer customer = branchDao.getCustomerByPanNumber(getCustomerByPanRequest.getPanNumber());
				if (customer != null) {
					return Utils.toJson(customer);
				} else {
					throw new InvalidRequestException(ErrorMessages.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_PAN_NUMBER.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while getting customer by pan number", ex);
			throw ex;
		}
	}

	public String getAccountByAccountNumber(String requestBody) throws Exception {

		try {
			GetAccountByAccountNumberRequest getAccountByAccountNumberRequest = (GetAccountByAccountNumberRequest) Utils.toObject(requestBody, GetAccountByAccountNumberRequest.class);
			if (!StringUtils.isEmpty(getAccountByAccountNumberRequest.getAccountNumber())) {
				BankAccount bankAccount = branchDao.getAccountByAccountNumber(getAccountByAccountNumberRequest.getAccountNumber());
				if (bankAccount != null) {
					return Utils.toJson(bankAccount);
				} else {
					throw new InvalidRequestException(ErrorMessages.ACCOUNT_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
				}
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_ACCOUNT_NUMBER.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while getting customer by pan number", ex);
			throw ex;
		}
	}

	private Branch updateBranchToDataBase(Branch branch) {

		return branchDao.saveBranch(branch);
	}

	private void populateBankAccountObject(BankAccount bankAccount, CreateBankAccountRequest createBankAccountRequest) throws InvalidRequestException {

		if (createBankAccountRequest.getType().equals(BankAccount.AccountType.SAVING)) {
			SavingAccountService savingAccountService = new SavingAccountService();
			if (savingAccountService.getMinimumBalance() <= createBankAccountRequest.getAmount()) {
				bankAccount.setCurrentBalance(createBankAccountRequest.getAmount());
			} else {
				throw new InvalidRequestException(ErrorMessages.INSUFFICIENT_AMOUNT.getMessage() + savingAccountService.getMinimumBalance(), HttpStatus.BAD_REQUEST);
			}
			bankAccount.setInterestRate(savingAccountService.getInterestRate());
			bankAccount.setMinimumBalance(savingAccountService.getMinimumBalance());
		} else {
			CurrentAccountService currentAccountService = new CurrentAccountService();
			if (currentAccountService.getMinimumBalance() <= createBankAccountRequest.getAmount()) {
				bankAccount.setCurrentBalance(createBankAccountRequest.getAmount());
			} else {
				throw new InvalidRequestException(ErrorMessages.INSUFFICIENT_AMOUNT.getMessage() + currentAccountService.getMinimumBalance(), HttpStatus.BAD_REQUEST);
			}
			bankAccount.setInterestRate(currentAccountService.getInterestRate());
			bankAccount.setMinimumBalance(currentAccountService.getMinimumBalance());
		}
		bankAccount.setAccountType(createBankAccountRequest.getType());
	}

	private void populateCustomerObject(Customer customer, BankAccount bankAccount, CreateBankAccountRequest createBankAccountRequest) {

		List<BankAccount> bankAccounts = new ArrayList<>();
		bankAccounts.add(bankAccount);
		customer.setAccounts(bankAccounts);
		customer.setPanNumber(createBankAccountRequest.getPanNumber());
	}
}
