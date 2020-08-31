package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.Product;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	@Override
	@EntityGraph(attributePaths = { "category", "vendor" })
	Page<Product> findAll(Specification<Product> specs, Pageable pageable);
	
}
