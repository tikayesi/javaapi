package com.sti.dao.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sti.dao.model.Customer;

import java.util.Date;

public class AccountDto {
	private int accountNumber;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	private Date openDate;
	public int balance;
	@JsonProperty("customer")
	public Customer customerId;
	
	public AccountDto() {
	}

	public AccountDto(Date openDate, int balance, Customer customerId) {
		super();
		this.openDate = openDate;
		this.balance = balance;
		this.customerId = customerId;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}
	
	
	
}
