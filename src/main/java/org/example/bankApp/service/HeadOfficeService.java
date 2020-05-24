package org.example.bankApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bankApp.data.HeadOfficeDao;
import org.example.bankApp.exception.ErrorMessages;
import org.example.bankApp.exception.InvalidRequestException;
import org.example.bankApp.exception.WrappedException;
import org.example.bankApp.model.Branch;
import org.example.bankApp.model.HeadOffice;
import org.example.bankApp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeadOfficeService {

	@Autowired
	HeadOffice headOffice;

	@Autowired
	HeadOfficeDao headOfficeDao;

	private static final Logger log = LogManager.getLogger(HeadOfficeService.class);

	public String createBranch() throws Exception {

		try {
			Branch branch = new Branch();

			Branch newBranch = headOfficeDao.createBranch(branch);

			if (newBranch != null && !StringUtils.isEmpty(newBranch.getBranchId())) {
				return Utils.toJson(branch);
			} else {
				throw new WrappedException(ErrorMessages.UNABLE_TO_CREATE_BRANCH.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception ex) {
			log.error("Error occurred while creating branch", ex);
			throw ex;
		}
	}

	public String getBranchById(Integer branchId) throws Exception {

		try {
			if (branchId != null && branchId > 0) {
				Branch branch = headOfficeDao.getBranchById(branchId);
				if (branch != null) {
					return Utils.toJson(branch);
				} else {
					throw new InvalidRequestException(ErrorMessages.BRANCH_NOT_FOUND.getMessage(), HttpStatus.BAD_REQUEST);
				}
			} else {
				throw new InvalidRequestException(ErrorMessages.INVALID_BRANCH_ID.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			log.error("Error occurred while getting branch by Id : " + branchId);
			throw ex;
		}
	}

	public String getAllBranches() throws JsonProcessingException {

		try {
			Iterable<Branch> branchIterable = headOfficeDao.getAllBranches();
			List<Branch> branches = new ArrayList<>();
			branchIterable.forEach(branches::add);
			headOffice.setBranches(branches);
			return Utils.toJson(headOffice);
		} catch (Exception ex) {
			log.error("Error occurred while getting all branches", ex);
			throw ex;
		}
	}
}
