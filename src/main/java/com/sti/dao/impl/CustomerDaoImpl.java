package com.sti.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.dao.api.CustomerDao;
import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Customer;
import com.sti.dao.repository.CustomerRepository;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao{
	
	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer getById(int id) throws ErrorException {
		return repository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) throws ErrorException {
		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) throws ErrorException {
		repository.delete(customer);
		
	}

	@Override
	public List<Customer> getList() throws ErrorException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		
		TypedQuery<Customer> q = em.createQuery(query);
		
		return q.getResultList();
	}
	

}
