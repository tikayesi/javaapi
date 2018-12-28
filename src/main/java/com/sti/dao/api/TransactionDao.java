package com.sti.dao.api;

import java.util.List;

import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Account;
import com.sti.dao.model.Transaction;

public interface TransactionDao {
	Transaction getById(int id) throws ErrorException;
	Transaction save(Transaction transaction) throws ErrorException;
	void delete(Transaction transaction) throws ErrorException;
	
	List<Transaction> getList() throws ErrorException;
	List<Transaction> getListByAccount(Account accountId) throws ErrorException;
	
}
