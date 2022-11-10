package com.ACMEFresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ACMEFresh.exceptions.CategoryException;
import com.ACMEFresh.model.Category;
import com.ACMEFresh.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public Category addCategory(Category category) throws CategoryException {

		Category exiCategory = categoryRepo.findByCategoryName(category.getCategoryName());
		if( exiCategory != null ) {
			throw new CategoryException("Category already exist!");
		}
		return categoryRepo.save(category);
	}

	@Override
	public String deleteCategory(Integer categoryId) throws CategoryException {

		Category category = categoryRepo.findById(categoryId).orElseThrow( ()-> new CategoryException("Category does not exist!"));
		
		categoryRepo.delete(category);
		
		return "Category deleted successfully!";
	}

}
