package com.sti.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.dao.api.TransactionDao;
import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Account;
import com.sti.dao.model.Transaction;
import com.sti.dao.repository.TransactionRepository;

public class TransactionDaoImpl extends BaseImpl implements TransactionDao {
	
	@Autowired
	private TransactionRepository repository;

	@Override
	public Transaction getById(int id) throws ErrorException {
		return repository.findOne(id);
	}

	@Override
	public Transaction save(Transaction transaction) throws ErrorException {
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws ErrorException {
		repository.delete(transaction);
		
	}

	@Override
	public List<Transaction> getList() throws ErrorException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		
		TypedQuery<Transaction> q = em.createQuery(query);
		
		return q.getResultList();
	}
	
	@Override
	public List<Transaction> getListByAccount(Account accountId) throws ErrorException {
		return repository.findByAccountId(accountId);
	}



}