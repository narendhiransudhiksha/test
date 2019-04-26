package com.rest.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.wallet.data.entity.Account;

/*
 * Account repository for data access layer
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByPlayerId(String id);
}
