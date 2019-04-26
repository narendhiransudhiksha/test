package com.rest.wallet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.wallet.data.entity.Account;
import com.rest.wallet.exception.AccountException;
import com.rest.wallet.service.AccountService;

/*
 * Controller Class for Account Handling
 */

@RestController
@RequestMapping("/v1")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

	/*
	 * Retrives Account Balance for a particular Player using Account Number
	 *
	 */
	@GetMapping(value = "/account/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Account getAccountByID(@PathVariable("id") String userID) throws AccountException {
		logger.debug("userID : " + userID);
		Account account = accountService.getBalanceById(userID);
		if (account != null) {
			return account;
		} else {
			throw new AccountException("Account : " + userID + " Does not Exist");
		}
	}

	// @GetMapping(value ="/account/{name}",
	// produces=MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	// public Account getAccountByName( @PathVariable("name") String userID) throws
	// AccountException {
	// logger.debug("userID : " +userID);
	// Account account = accountService.getBalanceById(userID);
	// if(account != null) {
	// return account;
	// }
	// else {
	// throw new AccountException("Account : "+userID + " Does not Exist");
	// }
	// }

}
