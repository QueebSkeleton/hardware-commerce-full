package com.github.queebskeleton.hardwarecommerce.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.queebskeleton.hardwarecommerce.dto.FrontStorePagination;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StoreProductsPaginationController {
	
	private final ProductService productService;
	
	@GetMapping("/products")
	public String products(FrontStorePagination paginationData, Pageable pageable, Model model) {
		model.addAttribute("productPage", productService.getProductPage(paginationData, pageable));
		
		return "front-store/pages/products-view";
	}

}
