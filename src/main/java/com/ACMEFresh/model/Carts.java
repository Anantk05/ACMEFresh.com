package com.ACMEFresh.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carts {

	@Id
	private Integer cartId;
	
	@OneToOne
	@JoinColumn( name = "customer_id", referencedColumnName = "customerId")
	private Customers customer;
	
	@OneToMany
	@JoinTable(name = "cart_products", joinColumns = @JoinColumn(name="cart_id", referencedColumnName = "cartId"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "productId"))
	private List<Products> products;
	
	
	
}
