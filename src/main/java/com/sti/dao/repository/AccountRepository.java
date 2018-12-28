package com.sti.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.sti.dao.model.Account;
import com.sti.dao.model.Customer;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findByAccountNumber(int accountNumber);
	List<Account> findByCustomerId(Customer customerId);
}
 