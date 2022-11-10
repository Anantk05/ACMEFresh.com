package com.ACMEFresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ACMEFresh.model.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {

	@Query("select od from OrderDetails od where od.orderId = ?1 and od.product.productId = ?2")
	public OrderDetails getOrderdetailsOfProduct( Integer orderId, Integer paymentId );
}
