package org.example.bankApp.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Component
public class Branch {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer branchId;

	@OneToMany (targetEntity = BankAccount.class, cascade = CascadeType.ALL)
	private List<BankAccount> bankAccounts;

	@OneToMany (targetEntity = Customer.class, cascade = CascadeType.ALL)
	private List<Customer> customers;

	public Integer getBranchId() {
		return branchId;
	}

	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Branch{" +
				"bankAccounts=" + bankAccounts +
				", customers=" + customers +
				", branchId='" + branchId + '\'' +
				'}';
	}
}
