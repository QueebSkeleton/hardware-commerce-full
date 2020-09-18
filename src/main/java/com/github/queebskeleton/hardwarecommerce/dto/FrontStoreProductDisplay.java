package com.github.queebskeleton.hardwarecommerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontStoreProductDisplay {
	
	private Long productId;
	private String categoryName;
	private String vendorName;
	private String productName;
	private String productDescription;
	private double productUnitPriceTaxed;
	private boolean isStocked;
	private List<String> imagePaths;

}
