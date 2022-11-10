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
public class Shippers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shipperId;
	
	@Size( min = 3, max = 25, message = "Please, Enter a valid company name.")
	private String companyName;
	
	@Size(min = 10, max = 10, message = "Mobile Number should be of 10 Characters!")
	private String phone;
	
}
