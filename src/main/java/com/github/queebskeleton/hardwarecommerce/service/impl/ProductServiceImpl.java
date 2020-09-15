package com.github.queebskeleton.hardwarecommerce.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.queebskeleton.hardwarecommerce.dto.AdminProductAddForm;
import com.github.queebskeleton.hardwarecommerce.dto.EntityImage;
import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.entity.ProductImage;
import com.github.queebskeleton.hardwarecommerce.entity.spec.ProductSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.ProductImageJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.ProductJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.ImageService;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductJpaRepository productJpaRepository;
	private final ProductImageJpaRepository productImageJpaRepository;
	
	private final ImageService imageService;

	@Override
	public Page<Product> getProductPage(Pageable pageable, String search) {
		
		Page<Product> productPage = (search == null) ?
				productJpaRepository.findAll(pageable) :
					productJpaRepository.findAll(
							Specification.where(ProductSpecs.nameContainsIgnoreCase(search))
							.or(ProductSpecs.categoryNameContainsIgnoreCase(search))
							.or(ProductSpecs.unitsInStockContains(search))
							.or(ProductSpecs.unitPriceContains(search)), pageable);
				
		productPage.getContent().forEach(product -> product.setImages(new ArrayList<>()));
		
		Map<Long, Product> productsMap =
				productPage.getContent()
						.parallelStream()
						.collect(
							Collectors.toMap(
									product -> product.getId(),
									product -> product));
		
		productImageJpaRepository
				.findByProduct_IdInFetchProduct(
						productPage.getContent()
							.parallelStream()
							.mapToLong(product -> product.getId())
							.boxed()
							.collect(Collectors.toList()))
				.forEach(productImage ->
						productsMap.get(productImage.getProduct().getId())
								.getImages()
								.add(productImage));
		
		return productPage;
		
	}

	@Override
	@Transactional
	public void addProduct(AdminProductAddForm addForm) {
		
		// Create Product
		Product product = new Product(
				null,
				addForm.getCategory(),
				addForm.getVendor(),
				addForm.getName(),
				addForm.getDescription(),
				addForm.getBarcode(),
				addForm.getStockKeepingUnit(),
				addForm.getInitialStock(),
				addForm.getUnitPrice(),
				addForm.isTaxable(),
				null);
		
		// If images exist, save product, 
		// retrieve UUID and use as file names for images,
		// then store images on local file system of server
		if(!addForm.getImages().get(0).isEmpty()) {
			product.setImages(
				addForm.getImages()
					.parallelStream()
					.map(image ->
						new ProductImage(
								null,
								image.getOriginalFilename().split("\\.")[1],
								product))
					.collect(Collectors.toList()));
			
			productJpaRepository.save(product);
			
			List<EntityImage> images = new ArrayList<>();
			
			for(int i = 0; i < addForm.getImages().size(); i++) {
				ProductImage imageMeta = product.getImages().get(i);
				try {
					images.add(
							new EntityImage(
									imageMeta.getFileName().toString(),
									imageMeta.getFileExtension(),
									addForm.getImages().get(i).getInputStream()));
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException("Error while trying to retrieve images.");
				}
			}
			
			// Save all product images
			imageService.saveAll(images);
		} else
			productJpaRepository.save(product);
		
	}

}
