package org.example.bankApp.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bankApp.service.HeadOfficeService;
import org.example.bankApp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeadOfficeController {

	@Autowired
	HeadOfficeService headOfficeService;

	private static final Logger log = LogManager.getLogger(HeadOfficeController.class);

	@PostMapping("/headoffice/create/branch")
	@ApiOperation("Create a new branch.")
	public ResponseEntity<String> createBranch() throws Exception {

		log.debug("Head office request received for creating new branch");

		String createBranchResponse = headOfficeService.createBranch();

		log.debug("Response for head office for creating new branch : " + createBranchResponse);
		return new ResponseEntity<>(createBranchResponse, Utils.getResponseHeaders(), HttpStatus.CREATED);
	}

	@GetMapping("/headoffice/get/{id}")
	@ApiOperation("Fetch branch by specific branch ID.")
	public ResponseEntity<String> getBranchById(@PathVariable ("id") Integer branchId) throws Exception {

		log.debug("Head office request received for getting branch by ID : " + branchId);

		String getBranchByIdResponse = headOfficeService.getBranchById(branchId);

		log.debug("Response for head office for getting branch by ID : " + getBranchByIdResponse);
		return new ResponseEntity<>(getBranchByIdResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}

	@GetMapping("/headoffice/get/all")
	@ApiOperation("Fetch all branches.")
	public ResponseEntity<String> getAllBranches() throws Exception {

		log.debug("Head office request received for getting all branches");

		String getAllBranchesResponse = headOfficeService.getAllBranches();

		log.debug("Response for head office for getting all branches : " + getAllBranchesResponse);
		return new ResponseEntity<>(getAllBranchesResponse, Utils.getResponseHeaders(), HttpStatus.OK);
	}
}
