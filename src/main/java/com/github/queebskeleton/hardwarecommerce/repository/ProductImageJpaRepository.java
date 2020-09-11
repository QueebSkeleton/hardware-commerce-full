package com.github.queebskeleton.hardwarecommerce.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.ProductImage;

@Repository
public interface ProductImageJpaRepository extends JpaRepository<ProductImage, UUID> {
	
	@EntityGraph(attributePaths = { "product" })
	@Query("SELECT i FROM ProductImage i LEFT JOIN FETCH i.product p WHERE p.id IN (?1)")
	List<ProductImage> findByProduct_IdInFetchProduct(List<Long> productIds);

}
