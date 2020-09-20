package com.github.queebskeleton.hardwarecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Rater {
	
	@Id
	private String emailAddress;
	
	private String name;

}
