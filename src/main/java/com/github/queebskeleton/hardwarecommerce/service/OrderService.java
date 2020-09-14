package com.github.queebskeleton.hardwarecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.entity.Order;

public interface OrderService {
	
	Page<Order> getOrderPage(Pageable pageable, String search);

}
