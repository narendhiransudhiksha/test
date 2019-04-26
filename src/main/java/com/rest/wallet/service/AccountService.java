package com.rest.wallet.service;

import javax.validation.Valid;

import com.rest.wallet.data.entity.Account;
import com.rest.wallet.data.entity.Transaction;
import com.rest.wallet.exception.*;


/*
 * Service Class for Account 
 */

public interface AccountService {

	public Account getBalanceById(String id);

	public Account validateAndUpdateAccount(@Valid Account account, @Valid Transaction transaction)
			throws TransactionException, AccountException;

}
