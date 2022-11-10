package com.ACMEFresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ACMEFresh.exceptions.ShipperException;
import com.ACMEFresh.model.Shippers;
import com.ACMEFresh.repository.ShipperRepo;

@Service
public class ShipperServiceImpl implements ShipperService {
	
	
	@Autowired
	ShipperRepo shipperRepo;

	@Override
	public Shippers addShippers(Shippers shipper) throws ShipperException {

		
		return shipperRepo.save(shipper);
	}

	@Override
	public String deleteShippers(Integer shipperId) throws ShipperException {

		Shippers shipper = shipperRepo.findById(shipperId).orElseThrow( ()-> new ShipperException("Shipper doesn't exist!"));
		
		shipperRepo.delete(shipper);
		
		return "Shipper deleted successfully!";
	}

	
}
