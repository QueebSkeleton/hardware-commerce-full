package com.github.queebskeleton.hardwarecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.entity.Category;

public interface CategoryService {
	
	Page<Category> getCategoryPage(Pageable pageable, String search);
	Category getCategoryById(Long id);
	void saveCategory(Category category);

}
