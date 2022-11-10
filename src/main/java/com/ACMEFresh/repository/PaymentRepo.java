package com.ACMEFresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ACMEFresh.model.Payments;

@Repository
public interface PaymentRepo extends JpaRepository<Payments, Integer> {

	
}
