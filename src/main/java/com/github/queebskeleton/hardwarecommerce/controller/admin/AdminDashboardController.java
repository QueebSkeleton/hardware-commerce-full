package com.github.queebskeleton.hardwarecommerce.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {

	// Redirect requests to index url, to administrator login page (by overriding)
	// TODO: Remove after creating e-commerce landing page
	@GetMapping("/")
	public String index() {
		return "redirect:/admin/login";
	}
	
}
