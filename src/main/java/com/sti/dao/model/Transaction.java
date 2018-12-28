package com.sti.dao.model;

import javax.persistence.*;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idtransaction")
	private int idTransaction;
	
	@Column(name="type")
	private String type;
	
	@Column(name="amount")
	public String amount;
	
	@Column(name="amountsign")
	public String amountSign;
	
	@ManyToOne
	@JoinColumn(name="accountId")
	public Account accountId;
	
	
	public int getIdTransaction() {
		return idTransaction;
	}
	
	public Transaction() {
		
	}
	
	public Transaction(Integer idTransaction) {
		this.idTransaction=idTransaction;
	}
	
	public Transaction(Integer idTransaction, String type, String amount, String amountSign, Account accountId) {
		this.idTransaction = idTransaction;
		this.type = type;
		this.amount = amount;
		this.amountSign=amountSign;
		this.accountId = accountId;
				
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

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}


	
}
	
	
	
	
	