package com.github.queebskeleton.hardwarecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.queebskeleton.hardwarecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ViewProductController {

	private final ProductService productService;
	
	@GetMapping("/product")
	public String viewProduct(Long id, Model model) {
		model.addAttribute("productDisplay", productService.getProductDisplayByProductId(id));
		
		return "front-store/pages/view-product";
	}
	
}
