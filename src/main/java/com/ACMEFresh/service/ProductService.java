package com.ACMEFresh.service;

import java.util.List;

import com.ACMEFresh.exceptions.ProductException;
import com.ACMEFresh.model.Products;

public interface ProductService {
		
	public Products addProduct(Products product) throws ProductException;
	
	public Products updateProduct(Products product) throws ProductException;
	
	public Products viewProduct(Integer id) throws ProductException;
	
	public List<Products> viewProductByCategory(String cName) throws ProductException;
	
	public String removeProduct(Integer pid)throws ProductException;

}
