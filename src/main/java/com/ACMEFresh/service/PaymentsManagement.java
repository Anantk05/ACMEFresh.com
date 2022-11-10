package com.ACMEFresh.service;

public class PaymentsManagement {
	
	
	
	public static Boolean payTotalAmmount( Double amount ) {
		
		if( amount > 0 ) {
			return true;
		}
		return false;
	}


}
