package com.ACMEFresh.controller;

import java.time.LocalDate;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ACMEFresh.exceptions.CartException;
import com.ACMEFresh.exceptions.CustomerException;
import com.ACMEFresh.exceptions.OrderException;
import com.ACMEFresh.exceptions.PaymentException;
import com.ACMEFresh.exceptions.ProductException;
import com.ACMEFresh.model.Carts;
import com.ACMEFresh.model.Customers;
import com.ACMEFresh.model.Orders;
import com.ACMEFresh.service.CartService;
import com.ACMEFresh.service.CustomerService;
import com.ACMEFresh.service.OrderService;
import com.ACMEFresh.service.SessionService;

@RestController
@CrossOrigin( origins = "*")
public class CustomersController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SessionService sessionService;
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customers> addCustomerHandler(@Valid @RequestBody Customers customer) throws CustomerException{

		Customers addedCustomer = customerService.addCustomer(customer);
		
		 return new ResponseEntity<Customers>(addedCustomer, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/customers/login/{userId}/{password}")
	public ResponseEntity<String> customerLoginHandler( @PathVariable String userId, @PathVariable String password) throws CustomerException, com.ACMEFresh.exceptions.LoginException{

		String res = sessionService.customerLogin(userId, password); 
		
		 return new ResponseEntity<String>(res, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/customers/logout")
	public ResponseEntity<String> customerLoginHandler( @RequestParam String uuid ) throws CustomerException, com.ACMEFresh.exceptions.LoginException{

		String res = sessionService.customerLogout(uuid);
		
		 return new ResponseEntity<String>(res, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customers> updateCustomerHandler(@PathVariable String key,@Valid @RequestBody Customers customer) throws LoginException, CustomerException, com.ACMEFresh.exceptions.LoginException{
		
		Customers updatedCustomer = customerService.updateCustomer(customer, key);
		
		return new ResponseEntity<Customers>(updatedCustomer, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<String> removeCustomerHandler(@RequestParam String key,@PathVariable Integer customerId ) throws CustomerException, com.ACMEFresh.exceptions.LoginException{
		
		String deletedCustomer = customerService.removeCustomer(customerId, key);
		
		return new ResponseEntity<String>(deletedCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/customers/{customerid}")
	public ResponseEntity<Customers> getCustomerHandler(@PathVariable Integer customerId) throws CustomerException{
		
		Customers existingCustomer = customerService.viewCustomer(customerId);
		
		return new ResponseEntity<Customers>(existingCustomer, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/carts/{productId}")
    public ResponseEntity<Carts> addToCartHandler(@PathVariable Integer productId, @RequestParam String key) throws CartException, ProductException, com.ACMEFresh.exceptions.LoginException{
    	
        Carts savedCart = cartService.addProductToCart(productId, key) ;

        return new ResponseEntity<>(savedCart, HttpStatus.OK);
    }
    @GetMapping("/carts")
    public ResponseEntity<Carts> getCartOfCustomerHandler(@RequestParam String key) throws CartException{
    	
    	Carts cart = cartService.getCartOfcustomer(key);
    	
    	return new ResponseEntity<Carts>(cart, HttpStatus.OK);
    }
    
    
    
    @DeleteMapping("/carts/{productId}")
    public ResponseEntity<Carts> removeAProductFromCart(@PathVariable Integer productId , 
    									      		   @RequestParam String key) throws CartException, ProductException, com.ACMEFresh.exceptions.LoginException {
    	
    	Carts list =cartService.removeProductFromCart(productId, key);
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    	
    }
    
    @PostMapping("/orders")
    public ResponseEntity<Orders> addOrderHandler(@Valid @RequestBody Carts order,@RequestParam String key, @RequestParam Integer paymentId ) throws OrderException, PaymentException, LoginException, CartException{
    	
    	Orders addedOrder = orderService.addOrder(order, key, paymentId);
    	
    	return new ResponseEntity<Orders>(addedOrder, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/orders/{orderid}/{productId}")
    public ResponseEntity<String> removeOrderHandler(@PathVariable Integer orderId, @PathVariable Integer productId, @RequestParam String uuid) throws OrderException{
    	
    	String deletedOrder = orderService.removeOrder(orderId, productId, uuid);
    	
    	return new ResponseEntity<String>(deletedOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Orders> viewOrderHandler(@PathVariable Integer orderId, @RequestParam String key) throws OrderException, LoginException{
    	
    	Orders existingOrder = orderService.viewOrder(orderId, key);
    	
    	return new ResponseEntity<Orders>(existingOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allorders{date}")
    public ResponseEntity<List<Orders>> getAllOrdersBydateHandler(@PathVariable LocalDate date) throws OrderException{
    	
    	List<Orders> orders = orderService.viewAllOrdersByDate(date);
    	
    	return new ResponseEntity<List<Orders>>(orders, HttpStatus.OK);
    	
    }

}
