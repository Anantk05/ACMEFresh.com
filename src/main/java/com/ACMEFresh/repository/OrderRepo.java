package com.ACMEFresh.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ACMEFresh.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer>{

	@Query("select o from Orders o where o.order_date like %?1%")
	public List<Orders> getOrderByDate(LocalDate date);

	
}
