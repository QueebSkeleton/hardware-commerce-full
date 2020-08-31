package com.github.queebskeleton.hardwarecommerce.service;

import com.github.queebskeleton.hardwarecommerce.entity.Product;

public interface ImageService {

	Product.Image getProductImage(Long id);
	
}
