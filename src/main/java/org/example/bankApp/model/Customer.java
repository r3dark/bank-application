package org.example.bankApp.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

/**
 * @author rohitsharma
 */

@Entity
@Component
public class Customer {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany (targetEntity = BankAccount.class, cascade = CascadeType.ALL)
	private List<BankAccount> accounts;

	private String panNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", accounts=" + accounts +
				", panNumber='" + panNumber + '\'' +
				'}';
	}
}
