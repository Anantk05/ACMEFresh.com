package com.ACMEFresh.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.security.auth.login.LoginException;

import com.ACMEFresh.exceptions.CartException;
import com.ACMEFresh.exceptions.OrderException;
import com.ACMEFresh.exceptions.PaymentException;
import com.ACMEFresh.model.Carts;
import com.ACMEFresh.model.Orders;

public interface OrderService {

	
		public Orders addOrder( Carts userCart, String key, Integer paymentId ) throws OrderException, CartException, LoginException, PaymentException;
				
		public String removeOrder(Integer oriderId, Integer productId, String key) throws OrderException;

		public Orders viewOrder(Integer orderId, String key) throws OrderException, LoginException;
		
		public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException;


	
}
