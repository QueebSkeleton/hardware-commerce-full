package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	@Repository
	public static interface ImageJpaRepository extends JpaRepository<Product.Image, Long> {
		
	}
	
}
