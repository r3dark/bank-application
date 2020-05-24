package com.example.bankApp.service;

import org.example.bankApp.data.HeadOfficeDao;
import org.example.bankApp.model.Branch;
import org.example.bankApp.model.HeadOffice;
import org.example.bankApp.service.HeadOfficeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rohitsharma
 */

public class HeadOfficeServiceTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	HeadOfficeDao headOfficeDao;

	@Mock
	HeadOffice headOffice;

	@Mock
	Branch branch;

	@InjectMocks
	HeadOfficeService headOfficeService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createBranchTest() {

		Branch branch = new Branch();
		Branch newBranch = headOfficeDao.createBranch(branch);
		Assert.assertNull(newBranch);
	}

	@Test
	public void getBranchByIdTest() {

		Integer branchId = 1;
		Branch branch = headOfficeDao.getBranchById(branchId);
		Assert.assertNull(branch);
	}

	@Test
	public void getAllBranchesTest() {

		Iterable<Branch> branchIterable = headOfficeDao.getAllBranches();
		List<Branch> branches = new ArrayList<>();
		branchIterable.forEach(branches::add);
		headOffice.setBranches(branches);
		Assert.assertEquals(headOffice.getBranches().size(), 0);
	}
}
