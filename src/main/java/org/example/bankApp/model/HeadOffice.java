package org.example.bankApp.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

@Component
public class HeadOffice {

	private List<Branch> branches;

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	@Override
	public String toString() {
		return "HeadOffice{" +
				"branches=" + branches +
				'}';
	}
}
