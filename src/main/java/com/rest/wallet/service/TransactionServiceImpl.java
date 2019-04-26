package com.rest.wallet.service;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rest.wallet.data.entity.Account;
import com.rest.wallet.data.entity.Transaction;
import com.rest.wallet.exception.AccountException;
import com.rest.wallet.exception.TransactionException;
import com.rest.wallet.repository.TransactionRepository;

/*
 * Service  Class for Transaction 
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountService accountService;

	@Override
	public List<Transaction> getTransactionById(String accountID) {
		return transactionRepository.findByAccountNumber(accountID);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = AccountException.class)

	@Override
	public Transaction saveTransaction(@Valid Transaction transactionObj)
			throws TransactionException, AccountException {
		String accountNum = transactionObj.getAccountNumber();
		String referenceId = "";
		// Check if the transaction Id exist
		Transaction transaction;
		Boolean validTransactionId = (transactionRepository
				.findByTransactionId(transactionObj.getTransactionId()) != null) ? false : true;
		if (!validTransactionId) {
			throw new TransactionException("Provided Transaction Id is an Invalid ");
		}
		try {
			// Get the Balance Amount for the Player
			Account account = accountService.getBalanceById(accountNum);
			referenceId = UUID.randomUUID().toString();
			Account accountRepository = accountService.validateAndUpdateAccount(account, transactionObj);
			transaction = new Transaction(transactionObj.getAccountNumber(), transactionObj.getAmount(),
					accountRepository.getUpdatedDate(), transactionObj.getTransactionType(),
					transactionObj.getTransactionId(), referenceId, transactionObj.getComment(), "Success");
			transactionRepository.save(transaction);
		} catch (NumberFormatException e) {
			throw new AccountException("Transaction Failed : " + e);
		}

		return transaction;
	}

}
