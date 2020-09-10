package com.github.queebskeleton.hardwarecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.github.queebskeleton.hardwarecommerce.entity.projection.ICategoryProductCount;
import com.github.queebskeleton.hardwarecommerce.entity.projection.IVendorProductCount;
import com.github.queebskeleton.hardwarecommerce.service.CategoryService;
import com.github.queebskeleton.hardwarecommerce.service.VendorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FrontStoreController {
	
	private final CategoryService categoryService;
	private final VendorService vendorService;
	
	@ModelAttribute("categoriesWithProductCount")
	public List<ICategoryProductCount> categoriesWithProductCount() {
		return categoryService.getAllCategoriesWithProductCount();
	}
	
	@ModelAttribute("vendorsWithProductCount")
	public List<IVendorProductCount> vendorsWithProductCount() {
		return vendorService.getAllVendorsWithProductCount();
	}
	
	@GetMapping("/store")
	public String store() {
		return "front-store/pages/store";
	}

}
