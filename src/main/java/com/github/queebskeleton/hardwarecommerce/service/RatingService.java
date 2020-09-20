package com.github.queebskeleton.hardwarecommerce.service;

import java.util.List;

import com.github.queebskeleton.hardwarecommerce.dto.FrontStoreRatingForm;
import com.github.queebskeleton.hardwarecommerce.entity.Rating;

public interface RatingService {
	
	List<Rating> getAllRatingsByProductId(Long productId);
	void rateProduct(FrontStoreRatingForm ratingForm);

}
