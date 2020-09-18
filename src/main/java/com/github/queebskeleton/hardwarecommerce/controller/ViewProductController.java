package com.github.queebskeleton.hardwarecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.queebskeleton.hardwarecommerce.dto.FrontStoreRatingForm;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;
import com.github.queebskeleton.hardwarecommerce.service.RatingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ViewProductController {

	private final RatingService ratingService;
	private final ProductService productService;
	
	@GetMapping("/product")
	public String viewProduct(Long id, Model model) {
		model.addAttribute("productDisplay", productService.getProductDisplayByProductId(id));
		
		return "front-store/pages/view-product";
	}
	
	@PostMapping("/rate")
	public String rateProduct(FrontStoreRatingForm ratingForm) {
		ratingService.rateProduct(ratingForm);
		
		return "redirect:/product?id=" + ratingForm.getProductId();
	}
	
}
