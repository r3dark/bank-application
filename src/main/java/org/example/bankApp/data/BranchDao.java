package org.example.bankApp.data;

import org.example.bankApp.model.BankAccount;
import org.example.bankApp.model.Branch;
import org.example.bankApp.model.Customer;
import org.example.bankApp.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class BranchDao {

	@Autowired
	BranchRepository branchRepository;

	public Iterable<Branch> getAllBranches() {
		return branchRepository.findAll();
	}

	public Customer getCustomerByPanNumber(String panNumber) {
		AtomicReference<Customer> customer = new AtomicReference<>();
		branchRepository.findAll()
			.forEach(branch -> branch.getCustomers()
				.forEach(customer1 -> {
					if (!StringUtils.isEmpty(customer1.getPanNumber()) && customer1.getPanNumber().equalsIgnoreCase(panNumber)) {
						customer.set(customer1);
					}
				})
			);
		return customer.get();
	}

	public BankAccount getAccountByAccountNumber(Integer accountNumber) {

		AtomicReference<BankAccount> account = new AtomicReference<>();
		branchRepository.findAll()
			.forEach(branch -> branch.getBankAccounts()
				.forEach(bankAccount -> {
					if (bankAccount.getAccountNumber() != null && bankAccount.getAccountNumber().equals(accountNumber)) {
						account.set(bankAccount);
					}
				})
			);
		return account.get();
	}

	public Branch saveBranch(Branch branch) {
		return branchRepository.save(branch);
	}
}
