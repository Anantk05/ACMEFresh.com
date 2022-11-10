package com.ACMEFresh.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ACMEFresh.exceptions.CartException;
import com.ACMEFresh.exceptions.LoginException;
import com.ACMEFresh.exceptions.ProductException;
import com.ACMEFresh.model.Carts;
import com.ACMEFresh.model.Customers;
import com.ACMEFresh.model.Products;
import com.ACMEFresh.model.UserSessions;
import com.ACMEFresh.repository.CartRepo;
import com.ACMEFresh.repository.CustomerRepo;
import com.ACMEFresh.repository.ProductRepo;
import com.ACMEFresh.repository.SessionRepo;

@Service
public class CartServiceImpl implements CartService{

	
	@Autowired
	CustomerRepo custRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	SessionRepo sessionRepo;
	
	@Autowired
	ProductRepo prodRepo;
	
	
	@Override
	public Carts addProductToCart(Integer productId, String key) throws CartException, ProductException, LoginException {
		
		UserSessions session = sessionRepo.findByUuid(key);
		
		if( session == null ) {
			throw new LoginException("Please Login first!");
		}
		Optional<Customers> customer = custRepo.findById(session.getCustomerId());
		
		Optional<Carts> cart = cartRepo.findById(customer.get().getCustomerId());
		
		Optional<Products> product = prodRepo.findById(productId);
		
		
		if( product.isEmpty() ) throw new ProductException("Product not available.");
		
		cart.get().getProducts().add(product.get());
		
		return cartRepo.save(cart.get());
		
	}

	@Override
	public Carts removeProductFromCart(Integer productId, String key) throws CartException, ProductException, LoginException {
		UserSessions session = sessionRepo.findByUuid(key);
		
		if( session == null ) {
			throw new LoginException("Please Login first!");
		}
		Optional<Customers> customer = custRepo.findById(session.getCustomerId());
		
		Optional<Carts> cart = cartRepo.findById(customer.get().getCustomerId());
		
		for( int prod=0; prod<cart.get().getProducts().size(); prod++ ) {
			
			if( (cart.get().getProducts().get(prod).getProductId()) == productId ) {
				cart.get().getProducts().remove(prod);
				break;
			}
		}
		
		return cartRepo.save(cart.get());
	}

	@Override
	public Carts getCartOfcustomer(String key) throws CartException {
		
		UserSessions session = sessionRepo.findByUuid(key);
		
		Carts exiCart = cartRepo.findById(session.getCustomerId()).orElseThrow( ()-> new CartException("Cart is empty!"));
		
		return exiCart;
	}

	
	
	
}