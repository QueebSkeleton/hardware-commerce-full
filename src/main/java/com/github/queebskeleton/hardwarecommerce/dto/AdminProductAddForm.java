package com.github.queebskeleton.hardwarecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import com.github.queebskeleton.hardwarecommerce.entity.Category;
import com.github.queebskeleton.hardwarecommerce.entity.Vendor;

import lombok.Data;

@Data
public class AdminProductAddForm {
	
	private Category category;
	
	private Vendor vendor;

	private String name;
	
	private String description;
	
	private String barcode;
	
	private String stockKeepingUnit;
	
	private int initialStock;
	
	private double unitPrice;
	
	private MultipartFile[] images;

}
