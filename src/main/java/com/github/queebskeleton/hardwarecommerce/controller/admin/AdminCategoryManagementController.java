package com.github.queebskeleton.hardwarecommerce.controller.admin;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.queebskeleton.hardwarecommerce.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryManagementController {
	
	private final CategoryService categoryService;
	
	@GetMapping("/table")
	public String table(Pageable pageable, Model model) {
		model.addAttribute("categoryPage", categoryService.getCategoryPage(pageable));
		
		return "admin/pages/category-mgmt/table";
	}
	
}
