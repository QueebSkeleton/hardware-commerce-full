package com.github.queebskeleton.hardwarecommerce.repository;

import org.springframework.stereotype.Repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaSpecificationExecutor;
import com.github.queebskeleton.hardwarecommerce.entity.Order;

@Repository
public interface OrderJpaRepository
		extends EntityGraphJpaRepository<Order, Long>,
			EntityGraphJpaSpecificationExecutor<Order> {

}
