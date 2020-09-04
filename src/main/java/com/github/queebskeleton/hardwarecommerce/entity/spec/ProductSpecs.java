package com.github.queebskeleton.hardwarecommerce.entity.spec;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.github.queebskeleton.hardwarecommerce.entity.Category;
import com.github.queebskeleton.hardwarecommerce.entity.Product;

public class ProductSpecs {
	
	public static Specification<Product> nameContainsIgnoreCase(String name) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
		};
	}
	
	public static Specification<Product> categoryNameContainsIgnoreCase(String categoryName) {
		return (root, query, criteriaBuilder) -> {
			Join<Product, Category> productCategoryJoin = root.join("category");
			return criteriaBuilder.like(
					criteriaBuilder.upper(productCategoryJoin.get("name")),
					"%" + categoryName.toUpperCase() + "%");
		};
	}
	
	public static Specification<Product> unitsInStockContains(String unitsInStock) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("unitsInStock"), unitsInStock);
		};
	}
	
	public static Specification<Product> unitPriceContains(String unitPrice) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.like(root.get("unitPrice"), unitPrice);
		};
	}

}
