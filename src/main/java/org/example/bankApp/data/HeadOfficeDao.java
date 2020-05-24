package org.example.bankApp.data;

import org.example.bankApp.model.Branch;
import org.example.bankApp.repository.HeadOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HeadOfficeDao {

	@Autowired
	HeadOfficeRepository headOfficeRepository;

	public Branch createBranch(Branch branch) {

		 return headOfficeRepository.save(branch);
	}

	public Branch getBranchById(Integer id) {

		Optional<Branch> branch = headOfficeRepository.findById(id);
		return branch.orElse(null);
	}

	public Iterable<Branch> getAllBranches() {
		return headOfficeRepository.findAll();
	}
}
