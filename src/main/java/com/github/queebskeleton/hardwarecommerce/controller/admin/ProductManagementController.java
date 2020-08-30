package com.github.queebskeleton.hardwarecommerce.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/products")
public class ProductManagementController {
	
	private final ProductService productService;
	
	@GetMapping("/table")
	public String table(Pageable pageable, String search, Model model) {
		Page<Product> productPage = productService.getProductPage(pageable, search);
		
		model.addAttribute("productPage", productPage);
		model.addAttribute("nameOrder", productPage.getSort().getOrderFor("name"));
		model.addAttribute("categoryNameOrder", productPage.getSort().getOrderFor("category.name"));
		model.addAttribute("unitsInStockOrder", productPage.getSort().getOrderFor("unitsInStock"));
		model.addAttribute("unitPriceOrder", productPage.getSort().getOrderFor("unitPrice"));
		
		return "admin/pages/product-mgmt/table";
	}

}
