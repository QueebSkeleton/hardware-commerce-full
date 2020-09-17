package com.github.queebskeleton.hardwarecommerce.controller.admin;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.queebskeleton.hardwarecommerce.dto.AdminCustomOrderForm;
import com.github.queebskeleton.hardwarecommerce.dto.Select2Response;
import com.github.queebskeleton.hardwarecommerce.entity.Product;
import com.github.queebskeleton.hardwarecommerce.entity.User;
import com.github.queebskeleton.hardwarecommerce.entity.spec.ProductSpecs;
import com.github.queebskeleton.hardwarecommerce.entity.spec.UserSpecs;
import com.github.queebskeleton.hardwarecommerce.repository.ProductJpaRepository;
import com.github.queebskeleton.hardwarecommerce.repository.UserJpaRepository;
import com.github.queebskeleton.hardwarecommerce.service.OrderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/orders/add-custom-form")
public class AddCustomOrderController {
	
	private final UserJpaRepository userJpaRepository;
	private final ProductJpaRepository productJpaRepository;
	
	private final OrderService orderService;
	
	@GetMapping
	public String addForm(Model model) {
		model.addAttribute("adminCustomOrderForm", new AdminCustomOrderForm());
		
		return "admin/pages/order-mgmt/add-custom-form/form";
	}

	@GetMapping("/users")
	public @ResponseBody Select2Response userOptions(Pageable pageable, String search) {
		
		Page<User> userPage = search == null ?
				userJpaRepository.findAll(pageable) :
					userJpaRepository.findAll(UserSpecs.nameContainsIgnoreCase(search), pageable);
		
		Select2Response response = new Select2Response();
		
		response.setResults(
				userPage.getContent()
						.parallelStream()
						.map(user ->
							new Select2Response.Content(
									user.getId().toString(),
									user.getFirstName()
										+ " "
										+ user.getLastName()))
						.collect(Collectors.toList()));
		response.setPagination(new Select2Response.Pagination(!userPage.isLast()));
		
		return response;
				
	}
	
	@GetMapping("/products")
	public @ResponseBody Select2Response productOptions(Pageable pageable, String search) {
		
		Page<Product> productPage = search == null ?
				productJpaRepository.findAll(pageable) :
					productJpaRepository.findAll(
							ProductSpecs.nameContainsIgnoreCase(search), pageable);
		
		Select2Response response = new Select2Response();
		
		response.setResults(
				productPage.getContent()
						.parallelStream()
						.map(product ->
							new Select2Response.Content(
									product.getId().toString(),
									product.getName()))
						.collect(Collectors.toList()));
		response.setPagination(new Select2Response.Pagination(!productPage.isLast()));
		
		return response;
		
	}
	
	@GetMapping("/order-item-row")
	public String orderItemRow(Long productId, int itemIndex, Model model) {
		model.addAttribute("product",
				productJpaRepository.findById(productId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid ID.")));
		model.addAttribute("itemIndex", itemIndex);
		
		return "admin/pages/order-mgmt/add-custom-form/order-item-row";
	}
	
	@PostMapping("/add")
	public String addOrder(AdminCustomOrderForm orderForm) {
		orderService.addOrder(orderForm);
		
		return "redirect:/admin/orders";
	}
	
}
