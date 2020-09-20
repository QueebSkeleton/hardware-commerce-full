package com.github.queebskeleton.hardwarecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.github.queebskeleton.hardwarecommerce.dto.BillingAddress;
import com.github.queebskeleton.hardwarecommerce.model.ShoppingCart;
import com.github.queebskeleton.hardwarecommerce.service.OrderService;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FrontStoreController {
	
	private final ProductService productService;
	private final OrderService orderService;
	
	@GetMapping
	public String landingPage(Model model) {
		model.addAttribute("topProductList", productService.getTopSellingProducts());
		model.addAttribute("newProductList", productService.getNewProducts());
		
		return "index";
	}
	
	@GetMapping("/checkout")
	public String checkoutPage() {
		return "front-store/pages/checkout";
	}
	
	@PostMapping("/checkout")
	public String checkout(
			BillingAddress billingAddress,
			@SessionAttribute ShoppingCart shoppingCart) {
		orderService.placeOrder(billingAddress, shoppingCart);
		
		return "redirect:/";
	}
	
}
