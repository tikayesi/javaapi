package com.sti.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.dao.api.AccountDao;
import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Account;
import com.sti.dao.model.Customer;
import com.sti.dao.repository.AccountRepository;

public class AccountDaoImpl extends BaseImpl implements AccountDao{
	@Autowired
	private AccountRepository repository;

	@Override
	public Account getById(int accountNumber) throws ErrorException {
			return repository.findOne(accountNumber);
	}

	@Override
	public Account save(Account account) throws ErrorException {
		return repository.save(account);
	}

	@Override
	public void delete(Account account) throws ErrorException {
		repository.delete(account);
		
	}

	@Override
	public List<Account> getList() throws ErrorException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = critB.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root);
		
		TypedQuery<Account> q = em.createQuery(query);
		
		return q.getResultList();
	}
	
	@Override
	public List<Account> getListByCustomer(Customer customerId) throws ErrorException {
		return repository.findByCustomerId(customerId);
	}

	
	

}
