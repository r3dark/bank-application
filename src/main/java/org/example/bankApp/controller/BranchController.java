package org.example.bankApp.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bankApp.service.BranchService;
import org.example.bankApp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rohitsharma
 */

@RestController
public class BranchController {

	@Autowired
	BranchService branchService;

	private static final Logger log = LogManager.getLogger(BranchController.class);

	@PostMapping("/branch/create/account")
	@ApiOperation("Create bank account in a branch.")
	public ResponseEntity<String> createBankAccount(@RequestBody String requestBody) throws Exception {

		log.debug("Request received for creating new account : " + requestBody);

		String createAccountResponse = branchService.createBankAccount(requestBody);

		log.debug("Bank account created successfully for request : " + requestBody);
		return new ResponseEntity<>(createAccountResponse, Utils.getResponseHeaders(), HttpStatus.CREATED);
	}

	@PostMapping ("/branch/get/customer")
	@ApiOperation("Fetch customer by PAN number.")
	public ResponseEntity<String> getCustomerByPanNumber(@RequestBody String requestBody) throws Exception {

		log.debug("Request received for getting customer by PAN number : " + requestBody);

		String getCustomerByPanResponse = branchService.getCustomerByPanNumber(requestBody);

		log.debug("Response for getting customer by PAN number : " + getCustomerByPanResponse);
		return new ResponseEntity<>(getCustomerByPanResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}

	@PostMapping ("/branch/get/account")
	@ApiOperation("Fetch account by account number.")
	public ResponseEntity<String> getAccountByAccountNumber(@RequestBody String requestBody) throws Exception {

		log.debug("Request received for getting account by account number : " + requestBody);

		String getAccountByAccountNumberResponse = branchService.getAccountByAccountNumber(requestBody);

		log.debug("Response for getting account by account number : " + getAccountByAccountNumberResponse);
		return new ResponseEntity<>(getAccountByAccountNumberResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}

}
