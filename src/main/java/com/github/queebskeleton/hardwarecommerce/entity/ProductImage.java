package com.github.queebskeleton.hardwarecommerce.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductImage {
	
	@Id
	@GeneratedValue(generator = "uuid_generator")
	@GenericGenerator(name = "uuid_generator", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID fileName;
	
	private String fileExtension;
	
	@ManyToOne
	private Product product;

}
