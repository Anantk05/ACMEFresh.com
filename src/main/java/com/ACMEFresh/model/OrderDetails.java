package com.ACMEFresh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

	@Id
	private Integer orderId;
	
	@OneToOne
	@JoinColumn( name = "product_id", referencedColumnName = "productId")
	private Products product;
	
	@Min( value = 1, message = "Quantity should be more than zero.")
	private Integer quantity;
	
	
	
}
