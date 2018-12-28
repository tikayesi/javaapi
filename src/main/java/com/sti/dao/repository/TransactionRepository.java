package com.sti.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.dao.model.Account;
import com.sti.dao.model.Transaction;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer> {
	Account findByIdTransaction(int idTransaction);
	List<Transaction> findByAccountId(Account accountId);
}
