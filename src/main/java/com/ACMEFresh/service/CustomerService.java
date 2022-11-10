package com.ACMEFresh.service;

import java.util.List;

import com.ACMEFresh.exceptions.CustomerException;
import com.ACMEFresh.exceptions.LoginException;
import com.ACMEFresh.model.Customers;

public interface CustomerService {

	public Customers addCustomer(Customers cust) throws CustomerException;
		
	public Customers updateCustomer(Customers cust, String key) throws CustomerException, LoginException  ;
	
	public String removeCustomer(Integer customerId, String key) throws CustomerException, LoginException;
	
	public Customers viewCustomer(Integer customerId)  throws CustomerException;
	
	public List<Customers> viewAllCustomerByLocation(String location) throws CustomerException ;

	
}
