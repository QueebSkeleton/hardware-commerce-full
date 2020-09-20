package com.github.queebskeleton.hardwarecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.github.queebskeleton.hardwarecommerce.entity.Product;

@Repository
public interface ProductJpaRepository extends EntityGraphJpaRepository<Product, Long>, EntityGraphJpaSpecificationExecutor<Product> {
	
	@Query("SELECT p FROM OrderItem o"
			+ " RIGHT OUTER JOIN o.product p"
			+ " LEFT OUTER JOIN FETCH p.category c"
			+ " GROUP BY p.id"
			+ " ORDER BY SUM(o.unitPrice * o.quantity) DESC")
	List<Product> findTopSaledProducts(Pageable pageable);
	
}
