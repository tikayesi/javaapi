package com.sti.dao.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="accountnumber")
	
	private int accountNumber;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	@Column(name="opendate")
	private Date openDate;
	
	@Column(name="balance")
	public int balance;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	@JsonProperty("customer")
	public Customer customerId;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public Account() {
		
	}
	
	public Account(Integer accountNumber) {
		this.accountNumber=accountNumber;
	}
	
	public Account(Integer accountNumber, Date openDate, Integer balance, Customer customerId) {
		this.accountNumber = accountNumber;
		this.openDate = openDate;
		this.balance = balance;
		this.customerId = customerId;
				
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

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	
}
