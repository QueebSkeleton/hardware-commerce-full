package com.github.queebskeleton.hardwarecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.entity.Category;
import com.github.queebskeleton.hardwarecommerce.entity.projection.ICategoryProductCount;

public interface CategoryService {
	
	Page<Category> getCategoryPage(Pageable pageable, String search);
	List<Category> getAllCategories();
	Category getCategoryById(Long id);
	void saveCategory(Category category);
	void deleteCategoryById(Long id);
	
	List<ICategoryProductCount> getAllCategoriesWithProductCount();

}
