package com.rest.wallet.service;

import java.util.List;

import javax.validation.Valid;

import com.rest.wallet.data.entity.Transaction;
import com.rest.wallet.exception.AccountException;
import com.rest.wallet.exception.TransactionException;

/*
 * Service Class for Transaction 
 */

public interface TransactionService {

	public List<Transaction> getTransactionById(String id);

	public Transaction saveTransaction(@Valid Transaction transaction) throws TransactionException, AccountException;
}
