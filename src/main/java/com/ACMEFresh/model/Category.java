package com.ACMEFresh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer categoryId;
	
	@Size( min = 3, max = 25, message = "Please, Enter a valid category name.")
	private String categoryName;
	
}
