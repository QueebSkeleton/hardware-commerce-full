package com.github.queebskeleton.hardwarecommerce.entity.spec;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.entity.Rating;

public class RatingSpecs {
	
	public static Specification<Rating> productIdEquals(Long productId) {
		return (root, query, builder) -> {
			Join<Rating, Product> ratingProductJoin = root.join("product");
			return builder.equal(ratingProductJoin.get("id"), productId);
		};
	}

}
