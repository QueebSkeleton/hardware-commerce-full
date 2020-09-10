package com.github.queebskeleton.hardwarecommerce.entity.spec;

import java.util.List;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.github.queebskeleton.hardwarecommerce.entity.Category;
import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.entity.Vendor;

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
	
	public static Specification<Product> categoryIdIn(List<Long> categoryIds) {
		return (root, query, criteriaBuilder) -> {
			Join<Product, Category> productCategoryJoin = root.join("category");
			return productCategoryJoin.get("id").in(categoryIds);
		};
	}
	
	public static Specification<Product> vendorIdIn(List<Long> vendorIds) {
		return (root, query, criteriaBuilder) -> {
			Join<Product, Vendor> productVendorJoin = root.join("vendor");
			return productVendorJoin.get("id").in(vendorIds);
		};
	}
	
	public static Specification<Product> unitPriceBetween(double min, double max) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.between(root.get("unitPrice"), min, max);
		};
	}

}
