package com.ACMEFresh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ACMEFresh.model.Customers;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer>{

	public Customers findByPhone( String phone);

	public Customers findByEmail(String email);

	@Query("select c from Customers c where city like %?1%")
	public List<Customers> getCustomerByLocation(String location);
}
