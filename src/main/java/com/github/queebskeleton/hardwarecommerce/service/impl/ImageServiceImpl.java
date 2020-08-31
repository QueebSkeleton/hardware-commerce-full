package com.github.queebskeleton.hardwarecommerce.service.impl;

import org.springframework.stereotype.Service;

import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.repository.ProductImageJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
	
	private final ProductImageJpaRepository productImageJpaRepository;

	@Override
	public Product.Image getProductImage(Long id) {
		return productImageJpaRepository.findById(id).orElseThrow(() ->
			new IllegalArgumentException("Invalid image id."));
	}

}
