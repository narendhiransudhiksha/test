package com.rest.wallet.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.wallet.data.dto.ResponseDTO;
import com.rest.wallet.data.entity.Transaction;
import com.rest.wallet.exception.AccountException;
import com.rest.wallet.exception.TransactionException;
import com.rest.wallet.service.TransactionService;

/*
 * Controller Class for Transaction Handling
 */
@RestController
@RequestMapping("/v1")
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransactionService transactionService;

	/*
	 * Transaction for a particular Player using Account Number.
	 * 
	 * 
	 */

	@GetMapping(value = "/transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Transaction> getTransactionByID(@PathVariable("id") String accountID) throws TransactionException {
		List<Transaction> transactionlist = transactionService.getTransactionById(accountID);
		if (!transactionlist.isEmpty()) {
			return transactionlist;
		} else {
			throw new TransactionException("No transaction record found for the Account : " + accountID);
		}
	}

	/*
	 * Saves the Transaction
	 * 
	 * eg:{ "accountnumber" : "100","amount" : "500", "transactiontype" : "Credit",
	 * "transactionId" : "0dfdfsssssdds3dss2", "comment" : "value" }
	 * 
	 */
	@PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseDTO saveTransaction(@Valid @RequestBody Transaction otransaction)
			throws AccountException, TransactionException {
		Transaction transaction = transactionService.saveTransaction(otransaction);
		if (transaction != null) {
			ResponseDTO responseDTO = new ResponseDTO(transaction.getReferenceId(), transaction.getTransactionDate(),
					transaction.getStatus());
			return responseDTO;
		} else {
			throw new TransactionException(
					"Transaction Exception occured for the Account : " + transaction.getAccountNumber());
		}

	}

}
