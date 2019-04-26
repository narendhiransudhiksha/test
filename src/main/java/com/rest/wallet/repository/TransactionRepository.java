package com.rest.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.wallet.data.entity.Transaction;

/*
 * Transaction repository for data access layer
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	List<Transaction> findByAccountNumber(String id);

	Transaction findByTransactionId(String transactionId);
}
