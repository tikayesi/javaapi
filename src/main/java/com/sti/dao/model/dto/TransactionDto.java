package com.sti.dao.model.dto;


import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sti.dao.model.Account;

public class TransactionDto {
	@Column(name="idtransaction")
	private int idTransaction;
	@Column(name="type")
	private String type;
	@Column(name="amount")
	private String amount;
	@Column (name="amountsign")
	private String amountSign;
	@ManyToOne
	@JoinColumn(name="accountId")
	@JsonProperty("account")
	private Account accountId;
	
	public TransactionDto() {}
	public TransactionDto(int idTransaction, String type, String amount,String amountSign, Account accountId) {
		this.idTransaction = idTransaction;
		this.type = type;
		this.amount = amount;
		this.amountSign = amountSign;
		this.accountId = accountId;
	}
	
	public int getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmountSign() {
		return amountSign;
	}
	public void setAmountSign(String amountSign) {
		this.amountSign = amountSign;
	}
	public Account getAccountId() {
		return accountId;
	}
	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}
}

