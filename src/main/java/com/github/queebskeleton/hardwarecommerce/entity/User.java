package com.github.queebskeleton.hardwarecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {
	
	static enum Type {
		ADMINISTRATOR,
		CUSTOMER;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String emailAddress;
	
	private String contactNumber;
	
	private String username;
	
	private String password;
	
}
