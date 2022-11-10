package com.ACMEFresh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ACMEFresh.exceptions.CustomerException;
import com.ACMEFresh.exceptions.LoginException;
import com.ACMEFresh.model.Carts;
import com.ACMEFresh.model.Customers;
import com.ACMEFresh.model.UserSessions;
import com.ACMEFresh.repository.CartRepo;
import com.ACMEFresh.repository.CustomerRepo;
import com.ACMEFresh.repository.SessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	SessionRepo sessionRepo;
	
	
	@Override
	public Customers addCustomer( Customers cust ) throws CustomerException {

		Customers custWithPhone = customerRepo.findByPhone(cust.getPhone());
		Customers custWithEmail = customerRepo.findByEmail(cust.getEmail());
		if( custWithPhone != null ) {
			throw new CustomerException("Already registered with this mobile! Please Login.");
		}
		if( custWithEmail != null ) {
			throw new CustomerException("Already registered with this email! Please Login.");
		}
		Customers savedCust = customerRepo.save(cust);
		
		Carts cart = new Carts();
		cart.setCartId(savedCust.getCustomerId());
		cart.setCustomer(savedCust);
		cartRepo.save(cart);
		
		return savedCust;
	}
	

	@Override
	public Customers updateCustomer( Customers cust, String key ) throws CustomerException, LoginException {
		
		UserSessions userSession = sessionRepo.findByUuid(key);
		if( userSession == null ) {
			throw new LoginException("Please! Login first.");
		}
		
		customerRepo.findById(cust.getCustomerId()).orElseThrow( ()-> new CustomerException("Customer not found!"));
		
		
		return customerRepo.save(cust);
	}

	@Override
	public String removeCustomer(Integer customerId, String key) throws CustomerException, LoginException {
		
		UserSessions userSession = sessionRepo.findByUuid(key);
		if( userSession == null ) {
			throw new LoginException("Please! Login first.");
		}
		
		Customers customer = customerRepo.findById(userSession.getCustomerId()).orElseThrow( ()-> new CustomerException("Customer not found!"));
		
		Optional<Carts> cart = cartRepo.findById(customerId);
		cartRepo.delete(cart.get());
		
		customerRepo.delete(customer);
		sessionRepo.delete(userSession);
		
		return "Account deleted successfully!";
	}

	@Override
	public Customers viewCustomer(Integer customerId) throws CustomerException {
		
		Customers customer = customerRepo.findById(customerId).orElseThrow( ()-> new CustomerException("Customer not found!"));
		
		return customer;
	}

	@Override
	public List<Customers> viewAllCustomerByLocation(String location) throws CustomerException {
		
		List<Customers> custList = customerRepo.getCustomerByLocation( location );
		
		if( custList.isEmpty() ) {
			new CustomerException("Record Not Found!");
		}
		return custList;
		
	}

	

	
}