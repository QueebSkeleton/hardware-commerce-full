package com.github.queebskeleton.hardwarecommerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.github.queebskeleton.hardwarecommerce.dto.FrontStoreRatingForm;
import com.github.queebskeleton.hardwarecommerce.entity.Rater;
import com.github.queebskeleton.hardwarecommerce.entity.Rating;
import com.github.queebskeleton.hardwarecommerce.entity.spec.RatingSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.ProductJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.RaterJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.RatingJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.RatingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RatingServiceImpl implements RatingService {

	private final ProductJpaRepository productJpaRepository;
	private final RaterJpaRepository raterJpaRepository;
	private final RatingJpaRepository ratingJpaRepository;

	@Override
	public List<Rating> getAllRatingsByProductId(Long productId) {
		return ratingJpaRepository.findAll(
				RatingSpecs.productIdEquals(productId),
				EntityGraphUtils.fromAttributePaths("rater"));
	}
	
	@Override
	@Transactional
	public void rateProduct(FrontStoreRatingForm ratingForm) {
		Rater rater = new Rater();
		rater.setEmailAddress(ratingForm.getRaterEmailAddress());
		rater.setName(ratingForm.getRaterName());
		
		raterJpaRepository.save(rater);
		
		Rating rating = new Rating();
		rating.setRater(rater);
		rating.setProduct(
				productJpaRepository.findById(ratingForm.getProductId())
					.orElseThrow(() -> new IllegalArgumentException("Invalid Product ID.")));
		rating.setReview(ratingForm.getReview());
		rating.setValue(ratingForm.getValue());
		
		ratingJpaRepository.save(rating);
	}
	
}
