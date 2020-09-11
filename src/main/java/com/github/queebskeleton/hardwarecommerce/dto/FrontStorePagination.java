package com.github.queebskeleton.hardwarecommerce.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontStorePagination {
	
	private List<Long> categoryIds;
	private List<Long> vendorIds;
	private Double minPrice;
	private Double maxPrice;

}
