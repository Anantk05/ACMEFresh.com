package com.ACMEFresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ACMEFresh.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	public Category findByCategoryName(String cName);

	
}
