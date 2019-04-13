package com.ryeh.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ryeh.springdemo.entity.Customer;

@Repository
public class CustomerDAOimpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query, sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastname", Customer.class);
		
		//execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer){
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId){
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// get object from database using primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);

		return theCustomer;
	}

}
