package com.ACMEFresh.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	@ManyToOne
	@JoinColumn(name="customer_id", referencedColumnName = "customerId")
	private Customers customer;
	
	@ManyToOne
	@JoinColumn(name="payment_id", referencedColumnName = "paymentId")
	private Payments payment;
	
	@ManyToOne
	@JoinColumn(name="shipper_id", referencedColumnName = "shipperId")
	private Shippers shipper;
	
	@PastOrPresent( message = "Please, Enter a valid order date.")
	private LocalDateTime order_date;
	
	@FutureOrPresent( message = "Please, Enter a valid ship date.")
	private LocalDateTime ship_date;
	
	@FutureOrPresent( message = "Please, Enter a valid delivery date.")
	private LocalDateTime delivery_date;
	
	@Min(value = 1, message = "Please, Enter a valid total price.")
	private Double total_order_price;
	
	
	
	
}


