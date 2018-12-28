package com.sti.dao.api;

import java.util.List;

import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Account;
import com.sti.dao.model.Customer;

public interface AccountDao {
	Account getById(int id) throws ErrorException;
	Account save(Account account) throws ErrorException;
	void delete(Account account) throws ErrorException;
	
	List<Account> getList() throws ErrorException;
	List<Account> getListByCustomer(Customer customerId) throws ErrorException;
	
}
