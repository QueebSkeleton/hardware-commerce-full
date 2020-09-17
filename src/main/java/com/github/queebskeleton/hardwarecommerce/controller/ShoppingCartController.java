package com.github.queebskeleton.hardwarecommerce.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.github.queebskeleton.hardwarecommerce.model.ShoppingCart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {
	
	private final ShoppingCart shoppingCart;
	
	@GetMapping("/add")
	public String addToCart(Long productId, int quantity) {
		shoppingCart.addItem(productId, quantity);
		
		return "redirect:/store";
	}
	
}
