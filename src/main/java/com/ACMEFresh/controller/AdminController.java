package com.ACMEFresh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ACMEFresh.exceptions.CategoryException;
import com.ACMEFresh.exceptions.CustomerException;
import com.ACMEFresh.exceptions.ProductException;
import com.ACMEFresh.model.Category;
import com.ACMEFresh.model.Customers;
import com.ACMEFresh.model.Products;
import com.ACMEFresh.service.CategoryService;
import com.ACMEFresh.service.CustomerService;
import com.ACMEFresh.service.ProductService;
import com.ACMEFresh.service.ShipperService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	ProductService productService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ShipperService shipperService;
	
	
	@PostMapping("/products")
    public ResponseEntity<Products> addProductHandler(@Valid @RequestBody Products product ) throws ProductException{
    	
    	Products addedProduct = productService.addProduct(product);
    	
    	return new ResponseEntity<Products>(addedProduct, HttpStatus.CREATED);
    	
    }
    
    @PutMapping("/products")
    public ResponseEntity<Products> updateProductHandler(@RequestBody Products product) throws ProductException{
    	
    	Products updatedProduct = productService.updateProduct(product);
    	
    	return new ResponseEntity<Products>(updatedProduct, HttpStatus.ACCEPTED);
    	
    }
    
    @GetMapping("/products/{productId}")
    public ResponseEntity<Products> getProductHandler(@PathVariable("productId") Integer productId) throws ProductException{
    	
    	Products exixtingProduct = productService.viewProduct(productId);
    	
    	return new ResponseEntity<Products>(exixtingProduct, HttpStatus.OK);
    	
    }
    
    @GetMapping("/products/category/{categoryName}")
    public ResponseEntity<List<Products>> getProductByCategoryHandler(@PathVariable("categoryName") String categoryName) throws ProductException{
    	
    	List<Products> categorywiseProducts = productService.viewProductByCategory(categoryName);
    	
    	return new ResponseEntity<List<Products>>(categorywiseProducts, HttpStatus.OK);
    	
    }
    
    
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProductHandler(@PathVariable("productId") Integer productId) throws ProductException{
    	
    	String res = productService.removeProduct(productId);
    	
    	return new ResponseEntity<String>(res, HttpStatus.OK);
    	
    }
    
    //   <----------	Category Handler ------------>
	
    @PostMapping("/category")
    public ResponseEntity<Category> addCategoryHandler(@Valid @RequestBody Category category ) throws CategoryException{
    	
    	Category addedCategory = categoryService.addCategory(category);
    	
    	return new ResponseEntity<Category>(addedCategory, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<String> deleteCategoryHandler(@PathVariable Integer categoryId) throws ProductException{
    	
    	String res = productService.removeProduct(categoryId);
    	
    	return new ResponseEntity<String>(res, HttpStatus.OK);
    	
    }
	
    //   <----------	Category Handler ------------>

    @PostMapping("/shippers")
    public ResponseEntity<Category> addShipperHandler(@Valid @RequestBody Category category ) throws CategoryException{
    	
    	Category addedCategory = categoryService.addCategory(category);
    	
    	return new ResponseEntity<Category>(addedCategory, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/shippers/{shipperId}")
    public ResponseEntity<String> deleteShipperHandler(@PathVariable Integer shipperId) throws ProductException{
    	
    	String res = productService.removeProduct(shipperId);
    	
    	return new ResponseEntity<String>(res, HttpStatus.OK);
    	
    }
	

	@GetMapping("/allCustomersByLocation/{city}")
	public ResponseEntity<List<Customers>> getAllCustomersByLocation(@PathVariable String city) throws CustomerException{
		
		
		List<Customers> customersByLocation = customerService.viewAllCustomerByLocation(city);
		
		return new ResponseEntity<List<Customers>>(customersByLocation, HttpStatus.OK);
		
	}

}
