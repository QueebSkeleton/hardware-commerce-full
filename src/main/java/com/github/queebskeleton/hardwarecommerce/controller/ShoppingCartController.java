package com.github.queebskeleton.hardwarecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.queebskeleton.hardwarecommerce.model.ShoppingCart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	
	private final ShoppingCart shoppingCart;
	
	@GetMapping("/add")
	public String addToCart(Long productId, int quantity) {
		shoppingCart.addItem(productId, quantity);
		
		return "redirect:/store";
	}
	
	@GetMapping("/remove")
	public String removeFromCart(Long productId) {
		shoppingCart.removeItem(productId);
		
		return "redirect:/store";
	}
	
}
