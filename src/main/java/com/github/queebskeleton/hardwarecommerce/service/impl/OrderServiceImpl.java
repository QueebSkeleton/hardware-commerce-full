package com.github.queebskeleton.hardwarecommerce.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.github.queebskeleton.hardwarecommerce.entity.Order;
import com.github.queebskeleton.hardwarecommerce.entity.spec.OrderSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.OrderJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

	private final OrderJpaRepository orderJpaRepository;
	
	@Override
	public Page<Order> getOrderPage(Pageable pageable, String search) {
		
		if(search == null)
			return orderJpaRepository.findAll(pageable,
					EntityGraphUtils.fromAttributePaths("placedBy"));
		
		return orderJpaRepository.findAll(
				Specification.where(
						OrderSpecs.userNameContainsIgnoreCase(search)
						.or(OrderSpecs.placedOnContainsIgnoreCase(search))
						.or(OrderSpecs.statusContainsIgnoreCase(search))),
				pageable,
				EntityGraphUtils.fromAttributePaths("placedBy"));
		
	}

}
