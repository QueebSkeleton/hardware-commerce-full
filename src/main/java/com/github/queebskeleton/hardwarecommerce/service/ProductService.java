package com.github.queebskeleton.hardwarecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.dto.AdminProductAddForm;
import com.github.queebskeleton.hardwarecommerce.dto.FrontStorePagination;
import com.github.queebskeleton.hardwarecommerce.dto.FrontStoreProductDisplay;
import com.github.queebskeleton.hardwarecommerce.entity.Product;

public interface ProductService {
	
	Page<Product> getProductPage(Pageable pageable, String search);
	Page<Product> getProductPage(FrontStorePagination paginationData, Pageable pageable);
	void addProduct(AdminProductAddForm addForm);
	
	FrontStoreProductDisplay getProductDisplayByProductId(Long productId);

}
