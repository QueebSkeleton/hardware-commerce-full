package com.github.queebskeleton.hardwarecommerce.service.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.github.queebskeleton.hardwarecommerce.dto.AdminCustomOrderForm;
import com.github.queebskeleton.hardwarecommerce.dto.OrderInvoice;
import com.github.queebskeleton.hardwarecommerce.entity.Order;
import com.github.queebskeleton.hardwarecommerce.entity.OrderItem;
import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.entity.spec.OrderSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.OrderItemJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.OrderJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.ProductJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.UserJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${general.settings.sales-tax-rate}")
	private double salesTaxRate;

	private final UserJpaRepository userJpaRepository;
	private final ProductJpaRepository productJpaRepository;
	private final OrderJpaRepository orderJpaRepository;
	private final OrderItemJpaRepository orderItemJpaRepository;
	
	@Override
	public Page<Order> getOrderPage(Pageable pageable, String search) {
		
		if(search == null)
			return orderJpaRepository.findAll(pageable,
					EntityGraphUtils.fromAttributePaths("placedBy"));
		
		return orderJpaRepository.findAll(
				Specification.where(
						OrderSpecs.idContainsIgnoreCase(search)
						.or(OrderSpecs.userNameContainsIgnoreCase(search))
						.or(OrderSpecs.placedOnContainsIgnoreCase(search))
						.or(OrderSpecs.statusContainsIgnoreCase(search))),
				pageable,
				EntityGraphUtils.fromAttributePaths("placedBy"));
		
	}
	
	@Override
	public OrderInvoice getOrderInvoiceByOrderId(Long id) {
		return OrderInvoice.fromOrderEntity(orderJpaRepository.findById(id,
				EntityGraphUtils.fromAttributePaths("placedBy", "orderItems", "orderItems.product"))
			.orElseThrow(() -> new IllegalArgumentException("Invalid Order ID.")));
	}

	@Override
	@Transactional
	public void addOrder(AdminCustomOrderForm orderForm) {
		Order order = new Order();
		
		order.setPlacedBy(
				userJpaRepository.findById(orderForm.getPlacedById())
					.orElseThrow(() -> new IllegalArgumentException("Invalid User ID.")));
		order.setPlacedOn(orderForm.getPlacedOn());
		order.setStatus(orderForm.getStatus());
		order.setSalesTaxRate(salesTaxRate);
		
		Map<Long, Product> products = 
				productJpaRepository.findAllById(
						orderForm.getOrderItems()
								 .parallelStream()
								 .mapToLong(orderItemForm -> orderItemForm.getProductId())
								 .boxed()
								 .collect(Collectors.toList()))
					.parallelStream()
					.collect(
							Collectors.toMap(
									product -> product.getId(),
									product -> product));
		
		order.setOrderItems(
				orderForm.getOrderItems()
						 .parallelStream()
						 .map(orderItemForm -> {
							 Product product = products.get(orderItemForm.getProductId());
							 OrderItem orderItem = new OrderItem(
									 null,
									 order,
									 product,
									 product.isTaxable(),
									 product.getUnitPrice(),
									 orderItemForm.getQuantity());
							 return orderItem;
						 }).collect(Collectors.toList()));
		
		// Persist Order
		orderJpaRepository.save(order);
		
		// Persist Order Items
		orderItemJpaRepository.saveAll(order.getOrderItems());
		
	}

	@Override
	public void deleteOrderById(Long id) {
		orderJpaRepository.deleteById(id);
	}

}
