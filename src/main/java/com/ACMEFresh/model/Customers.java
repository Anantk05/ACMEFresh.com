package com.ACMEFresh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer customerId;

	@Size( min = 3, max = 25, message = "Please, Enter a valid first name.")
	private String firstName;
	
	@Size( min = 3, max = 25, message = "Please, Enter a valid last name.")
	private String lastName;
	
	@Email( message = "Please, Enter a valid email!")
	private String email;
	
	@Size( min = 10, max = 10, message = "Mobile no. should be of 10 digit only.")
	private String phone;
	
	//@Pattern(regexp = "(^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[&!@#%*])){8,16}$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character from[&!@#%^$] and 1 digit")
	private String password;
	
	@Size( min = 5, max = 25, message = "Address should be of min 5 and max 25 characters.")
	private String address;
	
	@Size( min = 3, max = 25, message = "Please, Enter a valid city.")
	private String city;

	@Size( min = 2, max = 25, message = "Please, Enter a valid state.")
	private String state;
	
	@Size( min = 3, max = 25, message = "Please, Enter a valid country.")
	private String country;
	
	@Size( min = 6, max = 6, message = "Please, Enter only 6 characters Postalcode.")
	private String postalCode;
	
}
