package com.rest.wallet.service;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.wallet.data.entity.Transaction;
import com.rest.wallet.exception.AccountException;
import com.rest.wallet.exception.TransactionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {

	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionService transactionService;
	
	
	
	@Test
	public void debitTransaction() throws TransactionException, AccountException {
		Transaction transaction = new Transaction("111", "100", new Date(), "Debit", UUID.randomUUID().toString(), "11111", "debit", "Success");
		transactionService.saveTransaction(transaction);
		BigDecimal balance = accountService.getBalanceById("111").getBalanceAmount();
		 assertTrue(balance.doubleValue() == 100.0);
	}
	@Test
	public void creditTransaction() throws TransactionException, AccountException {
		Transaction transaction = new Transaction("111", "100", new Date(), "Credit", UUID.randomUUID().toString(), "11111", "credit", "Success");
		transactionService.saveTransaction(transaction);
		BigDecimal balance = accountService.getBalanceById("111").getBalanceAmount();
		 assertTrue(balance.doubleValue() == 200.0);
	}
	
	
}
