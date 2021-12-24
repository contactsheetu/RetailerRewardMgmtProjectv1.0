package com.retailer.rewardmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.retailer.rewardmgmt.model.TransactionModel;
import com.retailer.rewardmgmt.services.TransactionService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@Validated
public class TransactionController {

	@Autowired
	TransactionService transactionService;

	@PostMapping(path = "/addTransaction")
	public ResponseEntity<?> addTransaction(@RequestBody TransactionModel transactionModel) {
		log.info("[ addTransaction method {} ]");
		return new ResponseEntity<TransactionModel>(transactionService.addTransaction(transactionModel), HttpStatus.OK);

	}

	@GetMapping(path = "/getAllTransactions")
	public ResponseEntity<?> getAllTransactions() {
		log.info("[ getAllTransactions method {} ]");
		return new ResponseEntity<List<TransactionModel>>(transactionService.getAllTransactions(), HttpStatus.OK);

	}

	@GetMapping(path = "/getUserTransactions/userID/{userID}")
	public ResponseEntity<?> getUserTransactions(@PathVariable(value = "userID", required = true) long userID) {
		log.info("[ getUserTransactions method {} ]");
		return new ResponseEntity<List<TransactionModel>>(transactionService.getUserTransactions(userID),
				HttpStatus.OK);
	}

}
