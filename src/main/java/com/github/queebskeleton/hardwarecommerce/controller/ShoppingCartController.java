package com.github.queebskeleton.hardwarecommerce.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphUtils;
import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.model.ShoppingCart;
import com.github.queebskeleton.hardwarecommerce.repository.ProductJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {
	
	@Value("${general.settings.sales-tax-rate}")
	private double salesTaxRate;

	private final ProductJpaRepository productJpaRepository;
	private final ShoppingCart shoppingCart;
	
	@GetMapping("/add")
	public String addToCart(Long productId, int quantity) {
		// TODO: Move to a service layer
		Product product = productJpaRepository.findById(productId, EntityGraphUtils.fromAttributePaths("images"))
				.orElseThrow(() -> new IllegalArgumentException("Invalid Product ID."));
		
		shoppingCart.addItem(
				new ShoppingCart.Item(
						product.getId(),
						product.getName(),
						product.getImages() != null && !product.getImages().isEmpty() ?
								product.getImages().get(0).getFileName().toString() :
									null, 
						quantity,
						product.isTaxable() ?
							product.getUnitPrice() + (product.getUnitPrice() * salesTaxRate) :
								product.getUnitPrice()));
		
		return "redirect:/store";
	}
	
}
