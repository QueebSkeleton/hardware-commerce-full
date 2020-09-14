package com.github.queebskeleton.hardwarecommerce.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.queebskeleton.hardwarecommerce.entity.Order;
import com.github.queebskeleton.hardwarecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/orders")
public class OrderManagementController {

	private final OrderService orderService;
	
	@GetMapping("/table")
	public String table(Pageable pageable, String search, Model model) {
		Page<Order> orderPage = orderService.getOrderPage(pageable, search);
		
		model.addAttribute("orderPage", orderPage);
		model.addAttribute("placedByFirstNameOrder", orderPage.getSort().getOrderFor("placedBy.firstName"));
		model.addAttribute("placedOnOrder", orderPage.getSort().getOrderFor("placedOn"));
		model.addAttribute("statusOrder", orderPage.getSort().getOrderFor("status"));
		
		return "admin/pages/order-mgmt/table";
	}
	
}
