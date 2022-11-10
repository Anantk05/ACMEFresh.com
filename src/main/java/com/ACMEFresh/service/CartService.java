package com.ACMEFresh.service;

import com.ACMEFresh.exceptions.CartException;
import com.ACMEFresh.exceptions.LoginException;
import com.ACMEFresh.exceptions.ProductException;
import com.ACMEFresh.model.Carts;

public interface CartService {

	
	public Carts addProductToCart(Integer productId, String key) throws CartException, ProductException, LoginException ;
	
	public Carts removeProductFromCart(Integer productId,String key) throws CartException, ProductException, LoginException;

	public Carts getCartOfcustomer(String key) throws CartException;

		
	

}
