package com.sti.dao.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customernumber")
	private int customerNumber;
	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	public String lastName;
	@Column
	public String username;
	@Column
	public String password;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="EST")
	@Column(name="birthdate")
	public Date birthDate;
	@Column(name="phonenumber")
	public String phoneNumber;
	@Column(name="phonetype")
	public String phoneType;
	
	public Customer() {
		
	}
	
	public Customer(Integer customerNumber) {
		this.customerNumber=customerNumber;
	}
	
	public Customer(String firstName, String lastName, String username, String password, Date birthDate, String phoneNumber, String phoneType) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.phoneType = phoneType;
				
	}
	
	public int getCustomerNumber() {
		return customerNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getPhoneType() {
		return phoneType;
	}
	
	
	
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
}
