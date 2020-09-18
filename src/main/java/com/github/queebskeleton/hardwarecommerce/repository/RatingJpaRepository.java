package com.github.queebskeleton.hardwarecommerce.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.github.queebskeleton.hardwarecommerce.entity.Rating;

public interface RatingJpaRepository extends EntityGraphJpaRepository<Rating, Long> {

}
