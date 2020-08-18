package com.github.queebskeleton.hardwarecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		// Administrator - Login Page
		registry.addViewController("/admin/login").setViewName("admin/pages/login");
		
		// Administrator - Dashboard Page
		registry.addViewController("/admin/dashboard").setViewName("admin/pages/dashboard");
		
		// Administrator - Manage Categories Panel
		registry.addViewController("/admin/categories").setViewName("admin/pages/category-mgmt/panel");
		
	}

}
