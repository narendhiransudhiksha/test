package com.rest.wallet.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

/*
 * Transaction  Entity
 */
@Validated
@Entity
@Table(name = "player_transaction")
public class Transaction {

	@NotBlank(message = "Missing accountNumber field in the request")
	@Column(name = "account_number", nullable = false)
	private String accountNumber;
	@NotBlank(message = "Missing amount field in the request")
	@Column(name = "amount", nullable = false)
	private String amount;
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;
	@NotBlank(message = "Missing transactionType field in the request")
	@Column(name = "transaction_type", nullable = false)
	private String transactionType;
	@NotBlank(message = "Missing transactionId field in the request")
	@Column(name = "transaction_id", nullable = false)
	private String transactionId;
	@NonNull
	@Column(name = "reference_id", nullable = false)
	private String referenceId;
	@Column(name = "comment")
	private String comment;
	@Column(name = "status")
	private String status;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;

	public Transaction() {

	}

	public Transaction(String accountNumber, String ammount, Date transactionDate, String transactionType,
			String transactionId, String referenceId, String comment, String status) {
		this.accountNumber = accountNumber;
		this.amount = ammount;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionId = transactionId;
		this.referenceId = referenceId;
		this.comment = comment;
		this.status = status;
	}

	public Transaction(String accountNumber, String comment) {
		this.accountNumber = accountNumber;
		this.comment = comment;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
