package com.github.queebskeleton.hardwarecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.Category;
import com.github.queebskeleton.hardwarecommerce.entity.projection.ICategoryProductCount;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
	
	Page<Category> findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(
			String name,
			String description,
			Pageable pageable);
	
	@Query("SELECT c AS category, COUNT(p.id) AS totalProductCount FROM Product p RIGHT OUTER JOIN p.category c"
			+ " GROUP BY c.id")
	List<ICategoryProductCount> findAllWithProductCount();

}
