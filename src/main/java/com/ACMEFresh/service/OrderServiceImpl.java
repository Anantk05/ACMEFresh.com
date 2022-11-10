package com.ACMEFresh.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ACMEFresh.exceptions.CartException;
import com.ACMEFresh.exceptions.OrderException;
import com.ACMEFresh.exceptions.PaymentException;
import com.ACMEFresh.model.Carts;
import com.ACMEFresh.model.OrderDetails;
import com.ACMEFresh.model.Orders;
import com.ACMEFresh.model.Payments;
import com.ACMEFresh.model.UserSessions;
import com.ACMEFresh.repository.CartRepo;
import com.ACMEFresh.repository.CustomerRepo;
import com.ACMEFresh.repository.OrderDetailsRepo;
import com.ACMEFresh.repository.OrderRepo;
import com.ACMEFresh.repository.PaymentRepo;
import com.ACMEFresh.repository.SessionRepo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	OrderDetailsRepo oDetailsRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired
	PaymentRepo payRepo;
	
	Double totalAmount = 1.0;
	
	
	@Override
	public Orders addOrder(Carts userCart, String key, Integer paymentId) throws OrderException, CartException, LoginException, PaymentException {
		
		UserSessions session = sessionRepo.findByUuid(key);
		if( session == null ) {
			throw new LoginException("Please! Login first.");
		}
		Optional<Carts> items = cartRepo.findById(session.getCustomerId());
		totalAmount = 1.0;
		items.get().getProducts().forEach( (p) -> {
			totalAmount *= p.getSale_price();
		});
		
		Optional<Payments> payment = payRepo.findById(paymentId);
		if( payment.get().getAllowed().toUpperCase().equals("NO") ) {
			throw new PaymentException("This payment method is not availaable.");
		}
		if( !PaymentsManagement.payTotalAmmount(totalAmount) ) {
			throw new PaymentException("Transaction cancelled");
		}
		
		Orders newOrder = new Orders();
		
		newOrder.setCustomer(customerRepo.findById(session.getCustomerId()).get());
		newOrder.setOrder_date(LocalDateTime.now());
		newOrder.setPayment(payment.get());
		newOrder.setTotal_order_price(totalAmount);
		return orderRepo.save(newOrder);
	}

	@Override
	public String removeOrder(Integer oriderId, Integer productId, String key) throws OrderException {
		
		OrderDetails singleOrder = oDetailsRepo.getOrderdetailsOfProduct(oriderId, productId);
		
		if( singleOrder == null ) {
			throw new OrderException("Order is Already cancelled.");
		}
		return "Order cancelled successfully!";
	}

	@Override
	public Orders viewOrder(Integer orderId, String key) throws OrderException, LoginException {

		UserSessions userSession = sessionRepo.findByUuid(key);
		if( userSession == null ) {
			throw new LoginException("Please! Login first.");
		}
		Orders order = orderRepo.findById(orderId).orElseThrow( ()-> new OrderException("Order does not exist!"));
		return order;
	}

	@Override
	public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException {
		
		List<Orders> orderList = orderRepo.getOrderByDate( date );
		
		return orderList;
	}

	
}