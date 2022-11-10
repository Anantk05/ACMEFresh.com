package com.ACMEFresh.service;

import com.ACMEFresh.exceptions.CategoryException;
import com.ACMEFresh.model.Category;

public interface CategoryService {

	public Category addCategory( Category category ) throws CategoryException;
	
	public String deleteCategory( Integer categoryId ) throws CategoryException;
}
