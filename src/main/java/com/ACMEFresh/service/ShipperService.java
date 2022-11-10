package com.ACMEFresh.service;

import com.ACMEFresh.exceptions.ShipperException;
import com.ACMEFresh.model.Shippers;

public interface ShipperService {

	public Shippers addShippers( Shippers shipper ) throws ShipperException;
	
	public String deleteShippers( Integer shipperId) throws ShipperException;
	
}
