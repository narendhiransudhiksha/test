package com.rest.wallet.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.wallet.constants.Message;
import com.rest.wallet.constants.Variables;
import com.rest.wallet.data.entity.Account;
import com.rest.wallet.data.entity.Transaction;
import com.rest.wallet.exception.AccountException;
import com.rest.wallet.exception.TransactionException;
import com.rest.wallet.repository.AccountRepository;

/*
 * Service  Class for Account 
 */
@Service
public class AccountServiceImpl implements AccountService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getBalanceById(String id) {

		return accountRepository.findByPlayerId(id);
	}

	@Transactional(rollbackFor = AccountException.class)
	@Override
	public Account validateAndUpdateAccount(@Valid Account account, @Valid Transaction transaction)
			throws TransactionException, AccountException {
		// TODO Auto-generated method stub
		try {
			Boolean creditTransaction = transaction.getTransactionType().equalsIgnoreCase(Variables.CREDITTRANSACTION);
			logger.debug(" Transaction Type : " + transaction.getTransactionType());
			BigDecimal balanceAmount = account.getBalanceAmount();
			BigDecimal transactionAmt = new BigDecimal(transaction.getAmount()).abs();
			// Convert amount to negative value if its Debit transaction
			BigDecimal amount_for_transaction = (creditTransaction) ? transactionAmt : transactionAmt.negate();
			// Check if there is balance amount in the account
			Boolean validTransaction = (balanceAmount.compareTo(transactionAmt) >= 0 || creditTransaction);
			logger.debug(" Transaction Valid " + validTransaction);
			if (!validTransaction) {
				logger.debug("In-Sufficient Balance in the Account");
				throw new TransactionException(Message.INSUFFICIENT_AMOUNT);
			}

			account.setBalanceAmount(account.getBalanceAmount().add(amount_for_transaction));
			account.setUpdatedDate(new Date());
			return accountRepository.save(account);
		} catch (NumberFormatException e) {
			throw new AccountException(Message.INSUFFICIENT_AMOUNT);
		}
	}

}
