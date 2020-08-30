package com.github.queebskeleton.hardwarecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.entity.Product;

public interface ProductService {
	
	Page<Product> getProductPage(Pageable pageable, String search);

}
