package org.example.bankApp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bankApp.processor.AccountProcessor;
import org.example.bankApp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {

	@Autowired
	AccountProcessor accountProcessor;

	private static final Logger log = LogManager.getLogger(BankAccountController.class);

	@PostMapping("/account/withdraw")
	@ApiOperation("Withdraw money from bank account.")
	public ResponseEntity<String> withdraw(@RequestBody String requestBody) throws Exception {

		log.debug("Request received for withdrawing money : " + requestBody);

		String withdrawResponse = accountProcessor.accountTypeProcessor(requestBody, "withdraw");

		log.debug("Response for withdrawing money : " + withdrawResponse);
		return new ResponseEntity<>(withdrawResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}

	@PostMapping("/account/deposit")
	@ApiOperation("Deposit money to bank account.")
	public ResponseEntity<String> deposit(@RequestBody String requestBody) throws Exception {

		log.debug("Request received for depositing money : " + requestBody);

		String depositResponse = accountProcessor.accountTypeProcessor(requestBody, "deposit");

		log.debug("Response for depositing money : " + depositResponse);
		return new ResponseEntity<>(depositResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}

	@PostMapping("/account/transactions")
	@ApiOperation("Get transaction history of account.")
	public ResponseEntity<String> getTransactionHistory(@RequestBody String requestBody) throws Exception {

		log.debug("Request received for getting transaction history : " + requestBody);

		String transactionHistoryResponse = accountProcessor.accountStatementProcessor(requestBody, "transactionHistory");

		log.debug("Response for getting transaction history : " + transactionHistoryResponse);
		return new ResponseEntity<>(transactionHistoryResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}

	@PostMapping("/account/ministatement")
	@ApiOperation("Get mini statement of account (Last 10 transactions).")
	public ResponseEntity<String> getMiniStatement(@RequestBody String requestBody) throws Exception {

		log.debug("Request received for getting mini statement : " + requestBody);

		String miniStatementResponse = accountProcessor.accountStatementProcessor(requestBody, "miniStatement");

		log.debug("Response for getting mini statement: " + miniStatementResponse);
		return new ResponseEntity<>(miniStatementResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}
}
