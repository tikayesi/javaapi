package com.sti.dao.api;

import java.util.List;

import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Customer;

public interface CustomerDao {
	Customer getById(int id) throws ErrorException;
	Customer save(Customer customer) throws ErrorException;
	void delete(Customer customer) throws ErrorException;
	
	List<Customer> getList() throws ErrorException;
	
}
