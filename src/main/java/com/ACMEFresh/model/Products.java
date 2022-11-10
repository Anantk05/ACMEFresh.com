package com.ACMEFresh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer productId;
	
	@Size( min = 3, max = 55, message = "Please, Enter a valid product name.")
	private String productName;
	
	@Size( min = 3, max = 15, message = "Please, Provide valid nutrients for your product.")
	private String nutrients;
	
	
	@Size( min = 3, max = 15, message = "Please, Enter valid shelf life of your product.")
	private String shelfLife;
	
	@Min( value = 1, message = "Please, Enter a valid weight." )
	private Double weight;
	
	@ManyToOne
	@JoinColumn( name = "category_id", referencedColumnName = "categoryId")
	private Category category;
	
	@Min( value = 1, message = "Please, Enter a valid market price")
	private Double market_price;
	
	@Min( value = 1, message = "Please, Enter a valid sale price")
	private Double sale_price;
	
	private String type;
	
	
	
}
