package com.ACMEFresh.service;

import com.ACMEFresh.exceptions.CustomerException;
import com.ACMEFresh.exceptions.LoginException;

public interface SessionService {
	
	public String customerLogin( String userId, String password ) throws LoginException, CustomerException;

	public String customerLogout( String uuid ) throws LoginException;

}
