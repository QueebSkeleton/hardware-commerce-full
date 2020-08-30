package com.github.queebskeleton.hardwarecommerce.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.entity.spec.ProductSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.ProductJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductJpaRepository productJpaRepository;

	@Override
	public Page<Product> getProductPage(Pageable pageable, String search) {
		
		if(search == null)
			return productJpaRepository.findAll(pageable);
		
		return productJpaRepository.findAll(
				Specification.where(ProductSpecs.nameContainsIgnoreCase(search))
				.or(ProductSpecs.categoryNameContainsIgnoreCase(search))
				.or(ProductSpecs.unitsInStockContains(search))
				.or(ProductSpecs.unitPriceContains(search)), pageable);
		
	}

}
