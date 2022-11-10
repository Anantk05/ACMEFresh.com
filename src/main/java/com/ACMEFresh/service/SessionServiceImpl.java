package com.ACMEFresh.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ACMEFresh.exceptions.CustomerException;
import com.ACMEFresh.exceptions.LoginException;
import com.ACMEFresh.model.Customers;
import com.ACMEFresh.model.UserSessions;
import com.ACMEFresh.repository.CustomerRepo;
import com.ACMEFresh.repository.SessionRepo;

@Service
public class SessionServiceImpl implements SessionService {

	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	
	@Override
	public String customerLogin(String userId, String password) throws LoginException, CustomerException {

		Customers custWithUserId1 = customerRepo.findByPhone(userId);
		Customers custWithUserId2 = customerRepo.findByEmail(userId);
		
		if( custWithUserId1 == null && custWithUserId2 == null ) {
			throw new CustomerException("User not registered! Please register first.");
		}
		Integer customerId = 0;
		if( custWithUserId1 != null ) {
			if( !password.equals(custWithUserId1.getPassword()) ) {
				throw new LoginException("Wrong username or password!");
			}
			customerId = custWithUserId1.getCustomerId();
		}
		else {
			if( !password.equals(custWithUserId2.getPassword()) ) {
				throw new LoginException("Wrong username or password!");
			}
			customerId = custWithUserId2.getCustomerId();
		}
		Optional<UserSessions> userSession = sessionRepo.findById(customerId);
		
		if( userSession.isPresent() ) {
			return "Log in Successful";
		}
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		UserSessions newSession = new UserSessions(customerId, uuid, LocalDateTime.now() );
		
		sessionRepo.save(newSession);
		return "Log in Successful";
	}


	@Override
	public String customerLogout(String uuid) throws LoginException {

		UserSessions exiSession = sessionRepo.findByUuid(uuid);
		
		if( exiSession == null ) {
			throw new LoginException("User already Logged out.");
		}
		
		sessionRepo.delete(exiSession);
		
		return "Logout successful";
	}
}
