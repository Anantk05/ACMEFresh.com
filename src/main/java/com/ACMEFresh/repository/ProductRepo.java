package com.ACMEFresh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ACMEFresh.model.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {

	@Query("select p from Products p where p.category.categoryId = ?1")
	List<Products> getProductByCategory(Integer cName);

	
}
