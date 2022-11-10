package com.ACMEFresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ACMEFresh.model.Shippers;

@Repository
public interface ShipperRepo extends JpaRepository<Shippers, Integer>{

	
}
