package com.rest.wallet.data.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Account Entity
 */
@Entity
@Table(name = "player_account")
public class Account {

	@Id
	@Column(name = "player_id", nullable = false)
	private String playerId;
	@Column(name = "player_name", nullable = false)
	private String playerName;
	@Column(name = "balance_amount", nullable = false)
	private BigDecimal balanceAmount;
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;
	@Column(name = "update_by", nullable = false)
	private String updateBy;

	public Account() {

	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

}
