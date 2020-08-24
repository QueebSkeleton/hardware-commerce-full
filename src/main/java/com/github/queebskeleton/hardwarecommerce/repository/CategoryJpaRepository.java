package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.queebskeleton.hardwarecommerce.entity.Category;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
	
	Page<Category> findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(
			String name,
			String description,
			Pageable pageable);

}
