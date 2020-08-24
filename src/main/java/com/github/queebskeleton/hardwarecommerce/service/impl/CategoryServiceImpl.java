package com.github.queebskeleton.hardwarecommerce.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.queebskeleton.hardwarecommerce.entity.Category;
import com.github.queebskeleton.hardwarecommerce.repository.CategoryJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryJpaRepository categoryJpaRepository;
	
	@Override
	public Page<Category> getCategoryPage(Pageable pageable, String search) {
		
		if(search == null)
			return categoryJpaRepository.findAll(pageable);
		
		return categoryJpaRepository.
				findAllByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(
						search, search, pageable);
		
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryJpaRepository.findById(id).get();
	}

	@Override
	public void saveCategory(Category category) {
		categoryJpaRepository.save(category);
	}

	@Override
	public void deleteCategoryById(Long id) {
		categoryJpaRepository.deleteById(id);
	}

}
