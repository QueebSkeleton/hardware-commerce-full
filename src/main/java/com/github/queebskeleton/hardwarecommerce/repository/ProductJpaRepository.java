package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.github.queebskeleton.hardwarecommerce.entity.Product;

@Repository
public interface ProductJpaRepository extends EntityGraphJpaRepository<Product, Long>, EntityGraphJpaSpecificationExecutor<Product> {
	
}
