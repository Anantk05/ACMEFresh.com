package com.ACMEFresh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ACMEFresh.exceptions.ProductException;
import com.ACMEFresh.model.Category;
import com.ACMEFresh.model.Products;
import com.ACMEFresh.repository.CategoryRepo;
import com.ACMEFresh.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepo prodRepo;
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public Products addProduct(Products product) throws ProductException {
		
		return prodRepo.save(product);
	}

	@Override
	public Products updateProduct(Products product) throws ProductException {
		
		prodRepo.findById(product.getProductId()).orElseThrow( ()-> new ProductException("Product does not exist!"));
		
		return prodRepo.save(product);
	}

	@Override
	public Products viewProduct(Integer id) throws ProductException {

		return prodRepo.findById(id).orElseThrow( ()-> new ProductException("Product not found!"));
		
	}

	@Override
	public List<Products> viewProductByCategory(String cName) throws ProductException {

		Category category = categoryRepo.findByCategoryName(cName);
		
		return prodRepo.getProductByCategory( category.getCategoryId() );
	}

	@Override
	public String removeProduct(Integer pid) throws ProductException {

		Products prod = prodRepo.findById(pid).orElseThrow( ()-> new ProductException("Product does not exist!"));
		
		prodRepo.delete(prod);
		
		return "Product deleted successfully!";
	}
	
	
}