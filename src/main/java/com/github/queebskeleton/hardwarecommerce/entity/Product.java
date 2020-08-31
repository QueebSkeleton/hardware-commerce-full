package com.github.queebskeleton.hardwarecommerce.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Entity
	public static class Image {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@ManyToOne
		private Product product;
		
		private String extension;
		
		@Lob
		private byte[] data;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Vendor vendor;
	
	private String name;
	
	private String description;
	
	private String barcode;
	
	private String stockKeepingUnit;
	
	private int unitsInStock;
	
	private double unitPrice;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Image> images;

}
