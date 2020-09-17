package com.github.queebskeleton.hardwarecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.queebskeleton.hardwarecommerce.dto.AdminCustomOrderForm;
import com.github.queebskeleton.hardwarecommerce.dto.OrderInvoice;
import com.github.queebskeleton.hardwarecommerce.entity.Order;

public interface OrderService {
	
	Page<Order> getOrderPage(Pageable pageable, String search);
	OrderInvoice getOrderInvoiceByOrderId(Long id);
	void addOrder(AdminCustomOrderForm orderForm);
	void deleteOrderById(Long id);

}
