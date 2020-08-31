package com.github.queebskeleton.hardwarecommerce.service.impl;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.queebskeleton.hardwarecommerce.dto.AdminProductAddForm;
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

	@Override
	public void addProduct(AdminProductAddForm addForm) {
		Product product =
				new Product(
						null,
						addForm.getCategory(),
						addForm.getVendor(),
						addForm.getName(),
						addForm.getDescription(),
						addForm.getBarcode(),
						addForm.getStockKeepingUnit(),
						addForm.getInitialStock(),
						addForm.getUnitPrice(),
						new ArrayList<>());
		
		try {
			for(MultipartFile image : addForm.getImages())
				product.getImages().add(
						new Product.Image(
								null,
								product,
								image.getOriginalFilename().split("\\.")[1],
								image.getBytes()));
		} catch(IOException e) {
			throw new IllegalArgumentException("Invalid images given.");
		}
		
		productJpaRepository.save(product);
	}

}
